/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: controller for staff, handle any interactions
 * from the staff webpage
 */

package it.project.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import it.project.application.pojo.Attachment;
import it.project.application.pojo.Position;
import it.project.application.pojo.Request;
import it.project.application.pojo.Staff;
import it.project.application.service.IAttachmentService;
import it.project.application.service.IPositionService;
import it.project.application.service.IRequestService;
import it.project.application.service.IStaffService;
import it.project.application.util.JwtUtil;
import it.project.application.vo.RequestVo;
import it.project.application.vo.Result;
import it.project.application.vo.StaffPositionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/getStaffUserInfo")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestParam String token) {

        // Parse staff id from token
        String staffIdStr = jwtUtil.extractStudentId(token);
        Long staffId = Long.valueOf(staffIdStr);

        // Get staff entity from staff id
        Staff staff = staffService.getById(staffId);

        // Response with matched staff entity
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        if (staff != null) {
            data.put("id", staffId);
            data.put("name", staff.getName());
            data.put("email", staff.getEmail());
            data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

            response.put("code", 20000);
            response.put("data", data);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("code", 5008);
            response.put("message", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // this is used to render all the requests for coordinator, with the assumption that one
    // subject has one coordinator, and one can only be coordinator for one subject
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

    // similarly, this is used to render all related requests for tutor, all the subjects tutor is in
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

    // get the staff info
    @GetMapping("/getStaffDetail/{staffId}")
    public Result getStaffDetail(@PathVariable int staffId){
        Staff staff = staffService.getById(staffId);
        return Result.success(staff);
    }

    // update the staff preference on email setting, whether receive emails on new request, or flagged request
    @PutMapping("/changeStaffPreference/{staffId}")
    public Result updateStaffPreference(@RequestBody Staff staffPreference, @PathVariable int staffId) {
        // log.info("{}", staffPreference);
        Staff staff = staffService.getById(staffId);
        staff.setNewRequest(staffPreference.isNewRequest());
        staff.setFlaggedRequest(staffPreference.isFlaggedRequest());
        staffService.updateById(staff);
        return Result.success(staff);
    }

    // get all the staff in a particular subject, so that coordinator can configure the tutor's authority
    // on which type of the requests these tutors can see
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

    // After the coordinator changes the authority of staff, need to update it in database
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
