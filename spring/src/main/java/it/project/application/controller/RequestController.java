/**
 * Class Name: RequestController
 * Description: Controller for handling request manipulations
 * 
 * Author: Dennis Wang & He Shen
 * Date: 2023/10/11
 */

package it.project.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import it.project.application.form.RequestForm;
import it.project.application.pojo.Attachment;
import it.project.application.pojo.Request;
import it.project.application.service.IAttachmentService;
import it.project.application.service.IRequestService;
import it.project.application.vo.RequestVo;
import it.project.application.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController // RestFul
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private IRequestService requestService;

    @Autowired
    private IAttachmentService attachmentService;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RequestController.class);

    @GetMapping("/getRequests/{studentId}")
    public Result getRequests(@PathVariable int studentId, 
                              @RequestParam(required = false) String status,
                              @RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "20") int pageSize){
        log.info("{}", studentId);
        // Create a Page object with the desired page number and page size
        Page<Request> page = new Page<Request>(pageNum, pageSize);
    
        // Create a query wrapper filter the records with the specified studentId
        QueryWrapper<Request> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        if (status != null && !status.isEmpty()){
            queryWrapper.eq("status", status);
        }
        
    
        // query the database with pagination and the query condition
        IPage<Request> pageResult = requestService.page(page, queryWrapper);
    
        // convert the requests into specified form to return to the client side
        List<RequestVo> voList = pageResult.getRecords().stream().map(request -> {
            RequestVo requestVo = new RequestVo();
            BeanUtils.copyProperties(request, requestVo);

            // Get the attachments for the current request
            List<Attachment> attachmentsForRequest = attachmentService.getByRequestId(request.getRequestId());
            requestVo.setAttachments(attachmentsForRequest);

            return requestVo;
        }).collect(Collectors.toList());
    
        return Result.success(voList);  // directly returning the list of RequestVo
    }    

    @GetMapping("/getRequestDetail/{requestId}")
    public Result getRequestDetail(@PathVariable int requestId){
        RequestVo requestVo = new RequestVo();
        Request request = requestService.getById(requestId);
        QueryWrapper query = new QueryWrapper();
        query.eq("request_id", request.getRequestId());
        List<Attachment> attachments = attachmentService.list(query);
        BeanUtils.copyProperties(request, requestVo);
        requestVo.setAttachments(attachments);

        return Result.success(requestVo);
    }

    @PostMapping("/saveRequest")
    public Result saveRequest(@RequestBody RequestForm requestForm){
        log.info("{}", requestForm);
        Request request = new Request();
        BeanUtils.copyProperties(requestForm, request);
        request.setStatus("WAITING");
        requestService.saveMain(request, requestForm.getAttachments()); // save into the database

        // Response with matched subject entity
        Map<String, Object> data = new HashMap<>();

        // Copy request into response data structure
        data.put("requestId", request.getRequestId());
        data.put("requestType", request.getRequestType());
        data.put("workType", request.getWorkType());
        data.put("requestName", request.getRequestName());
        data.put("status", request.getStatus());
        data.put("description", request.getDescription());
        data.put("submissionDate", request.getSubmissionDate());
        data.put("attachments", requestForm.getAttachments());

        return Result.success("Request submitted successfully!", data);
    }

    @DeleteMapping("/deleteRequest/{requestId}")
    public Result deleteRequest(@PathVariable int requestId){
        requestService.removeById(requestId);
        return Result.success("Successfully deleted");
    }
}
