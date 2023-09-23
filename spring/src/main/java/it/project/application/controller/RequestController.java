package it.project.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import it.project.application.form.RequestForm;
import it.project.application.mapper.RequestMapper;
import it.project.application.pojo.Attachment;
import it.project.application.pojo.Request;
import it.project.application.service.IAttachmentService;
import it.project.application.service.IRequestService;
import it.project.application.vo.RequestVo;
import it.project.application.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController // RestFul
@Slf4j
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private IRequestService requestService;

    @Autowired
    private IAttachmentService attachmentService;

    @GetMapping("/getRequests/{studentId}")
    public Result getRequests(@PathVariable int studentId, @RequestParam(defaultValue = "waiting") String status,
                              @RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "20") int pageSize){
        log.info("{}", studentId);
        // Create a Page object with the desired page number and page size
        Page<Request> page = new Page<Request>(pageNum, pageSize);

        // Create a query wrapper filter the records with the specified studentId
        QueryWrapper<Request> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        if (Objects.equals(status, "WAITING")){
            queryWrapper.eq("status", status);
        } else {
            queryWrapper.ne("status", "WAITING");
        }

        // query the database with pagination and the query condition
        IPage<Request> pageResult = requestService.page(page, queryWrapper);

        // convert the requests into specified form to return to the client side
        List voList = pageResult.getRecords().stream().map(request -> {
            RequestVo requestVo = new RequestVo();
            BeanUtils.copyProperties(request, requestVo);
            return requestVo;
        }).collect(Collectors.toList());

        pageResult.setRecords(voList);

        return Result.success(pageResult);
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
        return Result.success("Request submitted successfully!");
    }

    @DeleteMapping("/deleteRequest/{requestId}")
    public Result deleteRequest(@PathVariable int requestId){
        requestService.removeById(requestId);
        return Result.success("Successfully deleted");
    }
}
