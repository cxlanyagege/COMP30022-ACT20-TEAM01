package com.example.it.project.controllers;

import com.example.it.project.pojo.Subject;
import com.example.it.project.service.ISubjectService;
import com.example.it.project.util.JwtUtil;
import com.example.it.project.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/getSubjectInfo")
    public ResponseEntity<Map<String, Object>> getSubjectInfo(@RequestParam String token) {

        // Parse subject id from token
        Long subjectId = jwtUtil.extractSubjectId(token);

        // Get subject entity from subject id
        Subject subject = subjectService.getById(subjectId);

        // Response with matched subject entity
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        if (subject != null) {
            data.put("subjectId", subjectId);
            data.put("subjectName", subject.getSubjectName());

            response.put("code", 20000);
            response.put("data", data);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("code", 5008);
            response.put("message", "Subject not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateApprovedRequests/{subjectId}")
    public Result updateRequest(@PathVariable Integer subjectId, @RequestBody Subject updatedSubject){
        Subject subject = subjectService.getById(subjectId);
        subject.setAssignmentRequest(updatedSubject.isAssignmentRequest());
        subject.setExamRequest(updatedSubject.isExamRequest());
        subject.setQuizRequest(updatedSubject.isQuizRequest());
        subject.setPersonalRequest(updatedSubject.isPersonalRequest());
        subject.setOthersRequest(updatedSubject.isOthersRequest());
        subjectService.updateById(subject);
        return Result.success(subject);
    }

    @GetMapping("/getSubjectDetail/{subjectId}")
    public Result getSubjectDetail(@PathVariable Integer subjectId){
        Subject subject = subjectService.getById(subjectId);
        return Result.success(subject);
    }
}
