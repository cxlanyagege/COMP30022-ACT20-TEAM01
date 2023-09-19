/**
 * Class Name: StudentController
 * Description: Controller for handling student related requests
 * 
 * Author: He Shen
 * Date: 2023/8/16
 * 
 * Note: Not finalized since there is no StaffController
 */

package it.project.application.controller;

import it.project.application.entity.Subject;
import it.project.application.entity.Student;
import it.project.application.service.StudentService;
import it.project.application.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8888")
public class StudentController {

    @Autowired
    private StudentService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Student user) {
        Student authenticatedUser = userService.authenticate(user.getName(),
                user.getPassword());
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        if (authenticatedUser != null) {
            // Authentication role
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("STUDENT"));
            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedUser.getName(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // JWT transmission
            String jwt = jwtUtil.generateToken(authenticatedUser.getId());
            data.put("token", jwt);
            response.put("data", data);

            // Login success
            response.put("code", 20000);
            return ResponseEntity.ok(response);
        } else {
            // Login failed
            // TODO: More specific error indication
            response.put("code", 50000);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout() {
        // Logout success
        Map<String, Object> response = new HashMap<>();
        response.put("code", 20000);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestParam String token) {

        // Parse student id from token
        String studentIdStr = jwtUtil.extractStudentId(token);
        Long studentId = Long.valueOf(studentIdStr);

        // Get student entity from student id
        Student student = userService.getUserInfo(studentId);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        if (student != null) {
            data.put("id", studentId);
            data.put("name", student.getName());
            data.put("email", student.getEmail());
            data.put("avatar", "Avatar");

            response.put("code", 20000);
            response.put("data", data);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("code", 5008);
            response.put("message", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
