package it.project.application.controller;

import it.project.application.entity.Subject;
import it.project.application.entity.Student;
import it.project.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Student user) {
        Student authenticatedUser = userService.authenticate(user.getUsername(),
                user.getPassword());
        Map<String, String> response = new HashMap<>();

        if (authenticatedUser != null) {
            // Login success
            response.put("message", "Login Successfully");
            return ResponseEntity.ok(response);
        } else {
            // Login failed
            // TODO: More specific error indication
            response.put("message", "Login Failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/getUserSubjects")
    public ResponseEntity<List<Subject>> getUserSubjects(@RequestParam Long userId) {
        List<Subject> subjects = userService.getSubjectsForUser(userId);
        return ResponseEntity.ok(subjects);
    }

}
