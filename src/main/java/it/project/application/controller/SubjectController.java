/**
 * Class Name: SubjectController
 * Description: Controller for handling subject informations
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/10/19
 */

package it.project.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.project.application.pojo.Subject;
import it.project.application.service.ISubjectService;
import it.project.application.util.JwtUtil;

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
    
}
