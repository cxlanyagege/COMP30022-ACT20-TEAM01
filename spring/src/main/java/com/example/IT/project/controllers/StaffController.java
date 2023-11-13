package com.example.it.project.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.it.project.pojo.Attachment;
import com.example.it.project.pojo.Position;
import com.example.it.project.pojo.Request;
import com.example.it.project.pojo.Staff;
import com.example.it.project.service.IAttachmentService;
import com.example.it.project.service.IPositionService;
import com.example.it.project.service.IRequestService;
import com.example.it.project.service.IStaffService;
import com.example.it.project.vo.RequestVo;
import com.example.it.project.vo.Result;
import com.example.it.project.vo.StaffPositionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private IPositionService positionService;

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

    @GetMapping("/getTutorRequests/{tutorId}")
    public Result getTutorRequests(@PathVariable int tutorId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("staff_id", tutorId);
        List<Position> tutorPositions = positionService.list(queryWrapper);
        List<Request> requests = new ArrayList<>();
        queryWrapper.clear();

        for (Position position: tutorPositions){
            if (position.isAssignmentRequest()){
                queryWrapper.eq("subject_id", position.getSubjectId());
                queryWrapper.eq("request_type", "Assignment");
                requests.addAll(requestService.list(queryWrapper));
                queryWrapper.clear();
            }
            if (position.isExamRequest()){
                queryWrapper.eq("subject_id", position.getSubjectId());
                queryWrapper.eq("request_type", "Exam");
                requests.addAll(requestService.list(queryWrapper));
                queryWrapper.clear();
            }
            if (position.isQuizRequest()){
                queryWrapper.eq("subject_id", position.getSubjectId());
                queryWrapper.eq("request_type", "Quiz");
                requests.addAll(requestService.list(queryWrapper));
                queryWrapper.clear();
            }
            if (position.isOthersRequest()){
                queryWrapper.eq("subject_id", position.getSubjectId());
                queryWrapper.eq("request_type", "Others");
                requests.addAll(requestService.list(queryWrapper));
                queryWrapper.clear();
            }
            if (position.isPersonalRequest()){
                queryWrapper.eq("subject_id", position.getSubjectId());
                queryWrapper.eq("request_type", "Personal");
                requests.addAll(requestService.list(queryWrapper));
                queryWrapper.clear();
            }
        }

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
        log.info("{}", staffPreference);
        Staff staff = staffService.getById(staffId);
        staff.setNewRequest(staffPreference.isNewRequest());
        staff.setFlaggedRequest(staffPreference.isFlaggedRequest());
        staffService.updateById(staff);
        return Result.success(staff);
    }

    @GetMapping("/getStaffRoleInfo/{subjectId}")
    public Result getStaffRoleInfo(@PathVariable int subjectId){
        QueryWrapper query1 = new QueryWrapper();
        query1.eq("subject_id", subjectId);
        List<Position> positions = positionService.list(query1);

        List voList = positions.stream().map(position -> {
            StaffPositionVo staffPositionVo = new StaffPositionVo();
            QueryWrapper query2 = new QueryWrapper();
            query2.eq("staff_id", position.getStaffId());
            Staff staff = staffService.getOne(query2);
            BeanUtils.copyProperties(position, staffPositionVo);
            staffPositionVo.setName(staff.getName());
            staffPositionVo.setEmail(staff.getEmail());
            return staffPositionVo;
        }).collect(Collectors.toList());

        return Result.success(voList);
    }

    @PutMapping("/updateStaffAuthority/{staffId}")
    public Result updateStaffAuthority(@PathVariable int staffId, @RequestBody Position authorities){
        QueryWrapper query = new QueryWrapper();
        query.eq("subject_id", authorities.getSubjectId());
        query.eq("staff_id", staffId);
        Position position = positionService.getOne(query);

        position.setAssignmentRequest(authorities.isAssignmentRequest());
        position.setExamRequest(authorities.isExamRequest());
        position.setOthersRequest(authorities.isOthersRequest());
        position.setQuizRequest(authorities.isQuizRequest());
        position.setPersonalRequest(authorities.isPersonalRequest());

        UpdateWrapper update = new UpdateWrapper();
        update.eq("subject_id", authorities.getSubjectId());
        update.eq("staff_id", staffId);
        positionService.update(position, update);
        return Result.success(position);
    }
}
