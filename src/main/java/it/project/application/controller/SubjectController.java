/**
 * Class Name: SubjectController
 * Description: Controller for subject, handle changes to the subject table in database
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/11/14
 */

package it.project.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.project.application.pojo.Subject;
import it.project.application.service.IPositionService;
import it.project.application.service.ISubjectService;
import it.project.application.util.JwtUtil;
import it.project.application.vo.Result;


@RestController
@RequestMapping("/api")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IPositionService positionService;

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

    // get all subjects from given tutor
    @GetMapping("/getAllSubjects/{tutorId}")
    public Result getAllSubjects(@PathVariable Integer tutorId) {
        // Get all subject ids given tutor
        List<Integer> subjectIds = positionService.getSubjectIdsByTutorId(tutorId);

        // Get all subjects given subject ids
        List<Subject> subjects = subjectService.listSubjectsByIds(subjectIds);

        // Return subject names list with succeed
        return Result.success(subjects);
    }
    
}
