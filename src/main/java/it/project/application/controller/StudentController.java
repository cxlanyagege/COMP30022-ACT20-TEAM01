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
public class StudentController {

    @Autowired
    private StudentService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Student user) {
        Student authenticatedUser = userService.authenticate(user.getName(),
                user.getPassword());
        Map<String, String> response = new HashMap<>();

        if (authenticatedUser != null) {
            // Authentication role
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("STUDENT"));
            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedUser.getName(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // JWT transmission
            String jwt = jwtUtil.generateToken(authenticatedUser.getId());
            response.put("jwt", jwt);

            // Login success
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
