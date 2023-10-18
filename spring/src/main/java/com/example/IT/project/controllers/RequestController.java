package com.example.it.project.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.it.project.form.RequestForm;
import com.example.it.project.pojo.Attachment;
import com.example.it.project.pojo.Request;
import com.example.it.project.pojo.Student;
import com.example.it.project.service.IAttachmentService;
import com.example.it.project.service.IEmailService;
import com.example.it.project.service.IRequestService;
import com.example.it.project.service.IStudentService;
import com.example.it.project.vo.Email;
import com.example.it.project.vo.RequestVo;
import com.example.it.project.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api")
public class RequestController {
    @Autowired
    private IRequestService requestService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IStudentService studentService;

    @GetMapping("/getRequests/{studentId}")
    public Result getRequests(@PathVariable int studentId, @RequestParam(required = false) String status){
        log.info("{}", studentId);

        // Create a query wrapper filter the records with the specified studentId
        QueryWrapper<Request> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        if (status != null){
            if (Objects.equals(status, "WAITING")){
                queryWrapper.eq("status", status);
            } else {
                queryWrapper.ne("status", "WAITING");
            }
        }

        List<Request> requests = requestService.list(queryWrapper);
        // convert the requests into specified form to return to the client side
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

    @GetMapping("/getRequestDetail/{requestId}")
    public Result getRequestDetail(@PathVariable int requestId){
        RequestVo requestVo = new RequestVo();
        Request request = requestService.getById(requestId);
        QueryWrapper query = new QueryWrapper();
        query.eq("request_id", request.getRequestId());
        List<Attachment> attachments = attachmentService.list(query);
        BeanUtils.copyProperties(request, requestVo);
        requestVo.setAttachments(attachments);
        return Result.success(requestVo);
    }

    @PostMapping("/saveRequest")
    public Result saveRequest(@RequestBody RequestForm requestForm){
        log.info("{}", requestForm);
        Request request = new Request();
        BeanUtils.copyProperties(requestForm, request);
        request.setStatus("WAITING");
        requestService.saveMain(request, requestForm.getAttachments()); // save into the database

        RequestVo requestVo = new RequestVo();
        BeanUtils.copyProperties(request, requestVo);
        requestVo.setAttachments(requestForm.getAttachments());

        // check the email preference for student who create the request
        Student student = studentService.getById(requestForm.getStudentId());
        if (student.getCreateRequest()){ // if want to receive the email
            sendConfirmationEmail(student.getEmail(), student.getName());
        }

        if (!requestForm.getTeammates().isEmpty()){
            for (String teammate: requestForm.getTeammates()){
                if (!teammate.isEmpty()){ // if need to send email to teammate
                    QueryWrapper query = new QueryWrapper();
                    query.eq("email", teammate);
                    Student teamMember = studentService.getOne(query);
                    if (teamMember != null){ // if the student with the input email exists
                        if (teamMember.getCreateRequest()){ // send email if the preference say so
                            sendCreateEmail(teamMember, student.getName());
                        }
                    } else { // the email is either not in the database or typed in the wrong email
                        sendProblemEmail(student, teamMember);
                    }
                }
            }
        }
        return Result.success("Request submitted successfully!", requestVo);
    }

    @DeleteMapping("/deleteRequest/{requestId}")
    public Result deleteRequest(@PathVariable int requestId){
        Request request = requestService.getById(requestId);
        Student student = studentService.getById(request.getStudentId());

        // send confirmation email to the student who created the request
        if (student.getDeleteRequest()){
            sendDeleteEmail(student);
        }

        // remove the request from the database
        requestService.removeById(requestId);
        return Result.success("Successfully deleted");
    }

    @PutMapping("/updateRequest/{requestId}")
    public Result updateRequest(@PathVariable int requestId, @RequestParam String status){
        Request request = requestService.getById(requestId);
        request.setStatus(status);
        requestService.updateById(request); // update the status stored in the database
        Student student = studentService.getById(request.getStudentId());

        // send confirmation email to the student who created the request
        if (student.getProcessRequest()){
            sendProcessEmail(student, request);
        }
        return Result.success(request);
    }

    private void sendConfirmationEmail(String studentEmail, String name){
        String msg = String.format(
                """
                        Hello %s, \s

                        You have successfully submitted a request.
                        Log in to your student request portal through LMS to check out the details of the request \s

                        Thanks-The studentRequestHub Team""",
                name);
        emailService.sendSimpleMail(new Email(studentEmail, msg, "Request submitted successfully!"));
    }

    private void sendCreateEmail(Student teammate, String requestSender){
        String msg = String.format(
                """
                        Hello %s, \s

                        Your team member %s has successfully submitted a request.
                        Log in to your student request portal through LMS to check out the details of the request \s

                        Thanks-The studentRequestHub Team""",
                teammate.getName(), requestSender);
        emailService.sendSimpleMail(new Email(teammate.getEmail(), msg, "Request submitted successfully!"));
    }

    private void sendDeleteEmail(Student student){
        String msg = String.format(
                """
                        Hello %s, \s

                        You have successfully deleted a request.
                        If you wish to resubmit the request you can send another request form through the portal.
                        Apologies for the action that can't be undo \s

                        Thanks-The studentRequestHub Team""",
                student.getName());
        emailService.sendSimpleMail(new Email(student.getEmail(), msg, "Request deleted successfully"));
    }

    private void sendProcessEmail(Student student, Request request){
        String msg = String.format(
                """
                        Hello %s, \s

                        Your recent request\s

                        Thanks-The studentRequestHub Team""",
                student.getName());
        emailService.sendSimpleMail(new Email(student.getEmail(), msg, "Request has been processed"));
    }

    private void sendProblemEmail(Student sender, Student teammate){
        String msg = String.format(
                """
                        Hello %s, \s

                        One of your team members' email %s is not found in the database.
                        So he/she is unable to receive the notification on your request submitted recently.
                        You may want to check the email above to see if it's correct or you can delete your current
                        request and submit the request again, this time, make sure you input the correct email address. \s

                        Thanks-The studentRequestHub Team""",
                sender.getName(), teammate.getEmail());
        emailService.sendSimpleMail(new Email(sender.getEmail(), msg, "Email invalid!"));
    }
}
