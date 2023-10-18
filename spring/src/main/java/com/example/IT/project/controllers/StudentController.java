package com.example.it.project.controllers;

import com.example.it.project.pojo.Student;
import com.example.it.project.service.IStudentService;
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
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/getUserInfo")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestParam String token) {

        // Parse student id from token
        String studentIdStr = jwtUtil.extractStudentId(token);
        Long studentId = Long.valueOf(studentIdStr);

        // Get student entity from student id
        Student student = studentService.getById(studentId);

        // Response with matched student entity
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        if (student != null) {
            data.put("id", studentId);
            data.put("name", student.getName());
            data.put("email", student.getEmail());
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

    @PutMapping("/changeStudentPreference/{studentId}")
    public Result updateStudentPreference(@RequestParam(defaultValue = "true") Boolean createRequest, @RequestParam(defaultValue = "true") Boolean deleteRequest,
                       @RequestParam(defaultValue = "true") Boolean processRequest, @PathVariable int studentId) {
        Student student = studentService.getById(studentId);
        student.setCreateRequest(createRequest);
        student.setDeleteRequest(deleteRequest);
        student.setProcessRequest(processRequest);
        studentService.updateById(student);
        return Result.success(student);
    }

}
