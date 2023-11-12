package it.project.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import it.project.application.pojo.Attachment;
import it.project.application.pojo.Request;
import it.project.application.pojo.Staff;
import it.project.application.service.IAttachmentService;
import it.project.application.service.IRequestService;
import it.project.application.service.IStaffService;
import it.project.application.vo.RequestVo;
import it.project.application.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api")
public class StaffController {

    @Autowired
    private IRequestService requestService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IStaffService staffService;

    // 应该是传进来一个staffid和一个subjectid，database里面搜索这个staff然后
    // 根据他在这个subject里面的role获取requests
    @GetMapping("/getSubjectRequests/{subjectId}")
    public Result getSubjectRequests(@PathVariable Integer subjectId){
        QueryWrapper<Request> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("subject_id", subjectId);

        List<Request> requests = requestService.list(queryWrapper);
        List voList = requests.stream().map(request -> {
            RequestVo requestVo = new RequestVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("request_id", request.getRequestId());
            List<Attachment> attachments = attachmentService.list(query);
            BeanUtils.copyProperties(request, requestVo);
            requestVo.setAttachments(attachments);
            return requestVo;
        }).collect(Collectors.toList());

        return Result.success(voList);
    }

    @GetMapping("/getStaffDetail/{staffId}")
    public Result getStaffDetail(@PathVariable int staffId){
        Staff staff = staffService.getById(staffId);
        return Result.success(staff);
    }

    @PutMapping("/changeStaffPreference/{staffId}")
    public Result updateStaffPreference(@RequestBody Staff staffPreference, @PathVariable int staffId) {
        // log.info("{}", staffPreference);
        Staff staff = staffService.getById(staffId);
        staff.setNewRequest(staffPreference.isNewRequest());
        staff.setFlaggedRequest(staffPreference.isFlaggedRequest());
        staffService.updateById(staff);
        return Result.success(staff);
    }
}
