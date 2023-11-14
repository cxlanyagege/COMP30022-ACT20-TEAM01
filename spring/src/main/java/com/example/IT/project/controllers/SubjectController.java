/**
 * Author: Dennis Wang @ He Shen
 * Last modified data: 2023-11-12
 * Description: controller for subject, handle changes to the subject table in database
 */

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

    // called when coordinator configure what type of requests this subject can have, for instance,
    // this subject has assignment but no quiz
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

    // get the subject detail
    @GetMapping("/getSubjectDetail/{subjectId}")
    public Result getSubjectDetail(@PathVariable Integer subjectId){
        Subject subject = subjectService.getById(subjectId);
        return Result.success(subject);
    }
}
