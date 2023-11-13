package com.example.it.project.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.it.project.pojo.AAP;
import com.example.it.project.pojo.Request;
import com.example.it.project.pojo.Student;
import com.example.it.project.service.IAAPService;
import com.example.it.project.service.IRequestService;
import com.example.it.project.service.IStudentService;
import com.example.it.project.util.JwtUtil;
import com.example.it.project.vo.Result;
import com.example.it.project.vo.StudentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IAAPService aapService;

    @Autowired
    private IRequestService requestService;

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

    // written by Dennis wang, used to update the student preferences on getting the email
    // notification
    @PutMapping("/changeStudentPreference/{studentId}")
    public Result updateStudentPreference(@RequestBody Student studentPreference, @PathVariable int studentId) {
        // log.info("{}", studentPreference);
        Student student = studentService.getById(studentId);
        student.setCreateRequest(studentPreference.isCreateRequest());
        student.setDeleteRequest(studentPreference.isDeleteRequest());
        student.setProcessRequest(studentPreference.isProcessRequest());
        studentService.updateById(student);
        return Result.success(student);
    }

    @GetMapping("/getStudentInfo/{studentId}")
    public Result getStudentDetails(@PathVariable int studentId, @RequestParam(required = false) Integer subjectId){
        // log.info("{}", subjectId);
        StudentVo studentVo = new StudentVo();
        Student student = studentService.getById(studentId);

        // get AAP related to the student
        QueryWrapper query = new QueryWrapper();
        query.eq("student_id", studentId);
        AAP aap = aapService.getOne(query);

        if (subjectId != null){
            query.eq("subject_id", subjectId);
        }
        // get request history related to the student, may need to change the
        // Request to RequestVo to have access to the uploaded files as well
        List<Request> requestHistory = requestService.list(query);

        BeanUtils.copyProperties(student, studentVo);
        studentVo.setAapAttachment(aap);
        studentVo.setRequestHistory(requestHistory);
        return Result.success(studentVo);
    }

}