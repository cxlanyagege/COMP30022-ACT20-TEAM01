/**
 * Class Name: RequestController
 * 
 * Description: controller for request, handle different actions 
 *              such as add, delete, detail and change
 * 
 * Author: Dennis Wang & He Shen
 * 
 * Date: 2023/10/20
 */

package it.project.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import it.project.application.form.ConfirmationForm;
import it.project.application.form.RequestForm;
import it.project.application.pojo.*;
import it.project.application.service.*;
import it.project.application.vo.Email;
import it.project.application.vo.RequestVo;
import it.project.application.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private ISubjectService subjectService;

    // get the requests related to a student from the database
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

    // get the detail of a specific request
    @GetMapping("/getRequestDetail/{requestId}")
    public Result getRequestDetail(@PathVariable int requestId){
        RequestVo requestVo = new RequestVo();
        Request request = requestService.getById(requestId);
        QueryWrapper query = new QueryWrapper();
        query.eq("request_id", request.getRequestId());

        // get the attachments related to a request as well
        List<Attachment> attachments = attachmentService.list(query);
        BeanUtils.copyProperties(request, requestVo);
        requestVo.setAttachments(attachments);
        return Result.success(requestVo);
    }

    // save the request into the database
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
        if (student.isCreateRequest()){ // if want to receive the email
            sendConfirmationEmail(student.getEmail(), student.getName());
        }

        if (!requestForm.getTeammates().isEmpty()){
            for (String teammate: requestForm.getTeammates()){
                if (!teammate.isEmpty()){ // if need to send email to teammate
                    QueryWrapper query = new QueryWrapper();
                    query.eq("email", teammate);
                    Student teamMember = studentService.getOne(query);
                    if (teamMember != null){ // if the student with the input email exists
                        if (teamMember.isCreateRequest()){ // send email if the preference say so
                            sendCreateEmail(teamMember, student.getName());
                        }
                    } else { // the email is either not in the database or typed in the wrong email
                        sendProblemEmail(student, teammate);
                    }
                }
            }
        }

        // send email to staff if they want to receive emails if new request is made
        QueryWrapper newQuery = new QueryWrapper();
        newQuery.eq("subject_id", requestForm.getSubjectId());
        List<Position> positions = positionService.list(newQuery); // get all the staff in this subject
        Subject subject = subjectService.getById(requestForm.getSubjectId());
        for (Position position: positions){
            if (Objects.equals(requestForm.getRequestType(), "Assignment") && position.isAssignmentRequest()){
                Staff staff = staffService.getById(position.getStaffId());
                newCreatedEmail(staff, subject.getSubjectName());
            } else if (Objects.equals(requestForm.getRequestType(), "Exam") && position.isExamRequest()){
                Staff staff = staffService.getById(position.getStaffId());
                newCreatedEmail(staff, subject.getSubjectName());
            } else if (Objects.equals(requestForm.getRequestType(), "Quiz") && position.isQuizRequest()){
                Staff staff = staffService.getById(position.getStaffId());
                newCreatedEmail(staff, subject.getSubjectName());
            } else if (Objects.equals(requestForm.getRequestType(), "Others") && position.isOthersRequest()){
                Staff staff = staffService.getById(position.getStaffId());
                newCreatedEmail(staff, subject.getSubjectName());
            } else if (Objects.equals(requestForm.getRequestType(), "Personal") && position.isPersonalRequest()){
                Staff staff = staffService.getById(position.getStaffId());
                newCreatedEmail(staff, subject.getSubjectName());
            }
        }

        return Result.success("Request submitted successfully!", requestVo);
    }

    // delete a certain request from the database
    @DeleteMapping("/deleteRequest/{requestId}")
    public Result deleteRequest(@PathVariable int requestId){
        Request request = requestService.getById(requestId);
        Student student = studentService.getById(request.getStudentId());

        // send confirmation email to the student who created the request
        if (student.isDeleteRequest()){
            sendDeleteEmail(student);
        }

        // remove the request from the database
        requestService.removeById(requestId);
        return Result.success("Successfully deleted");
    }

    // update the request status after the request has been approved
    @PutMapping("/updateRequest/{requestId}")
    public Result updateRequest(@PathVariable int requestId, @RequestBody ConfirmationForm confirmationForm){
        Request request = requestService.getById(requestId);
        request.setStatus(confirmationForm.getStatus());
        requestService.updateById(request); // update the status stored in the database
        Student student = studentService.getById(request.getStudentId());

        // send confirmation email to the student who created the request
        if (student.isProcessRequest()){
            sendProcessEmail(student, request, confirmationForm.getMessage());
        }
        return Result.success(request);
    }

    // confirmation email on submitting a request
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

    // confirmation email to teammates if any
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

    private void newCreatedEmail(Staff staff, String subjectName){
        String msg = String.format(
                """
                        Hello %s, \s
                        A recent request about %s has been added into the cue.
                        Log in to your request portal through LMS to check out the details of the request \s
                        Thanks-The studentRequestHub Team""",
                staff.getName(), subjectName);
        emailService.sendSimpleMail(new Email(staff.getEmail(), msg, "New request has been made"));
    }

    // if want to receive the email if delete a request
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

    // email notification on request been processed
    private void sendProcessEmail(Student student, Request request, String message){
        String msg = String.format(
                """
                        Hello %s, \s

                        Your recent request id: %d, name: %s has been processed and now the status is %s.
                        Here's your tutor's response: %s.
                        Log in to your LMS to see the details of the request\s

                        Thanks-The studentRequestHub Team""",
                student.getName(), request.getRequestId(), request.getRequestName(), request.getStatus(), message);
        emailService.sendSimpleMail(new Email(student.getEmail(), msg, "Request has been processed"));
    }

    // email to the original sender notifying the wrong input for the teammates'emails
    private void sendProblemEmail(Student sender, String teammate){
        String msg = String.format(
                """
                        Hello %s, \s

                        One of your team members' email %s is not found in the database.
                        So he/she is unable to receive the notification on your request submitted recently.
                        You may want to check the email above to see if it's correct or you can delete your current
                        request and submit the request again, this time, make sure you input the correct email address. \s
                        
                        Thanks-The studentRequestHub Team""",
                sender.getName(), teammate);
        emailService.sendSimpleMail(new Email(sender.getEmail(), msg, "Email invalid!"));
    }
}
