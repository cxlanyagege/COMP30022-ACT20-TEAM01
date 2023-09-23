package com.example.IT.project.form;

import com.example.IT.project.pojo.Attachment;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class RequestForm {
    // from client to server
    private String description;
    private Integer requestId;
    private Integer studentId;
    private String subjectId;
    private Date submissionDate;
    private String requestType;
    private String requestName;
    private String taskType;
    private List<Attachment> attachments;
}
