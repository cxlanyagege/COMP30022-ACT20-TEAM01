package it.project.application.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

import it.project.application.pojo.Attachment;

@Data
public class RequestVo {
    // from server to client
    // 返回的如果是一个class，springboot会自动把class转换成JSON格式
    private String description;
    // private String subjectId;
    private Integer studentId;
    private Date submissionDate;
    private String status;
    private String requestType;
    private String requestName;
    private Integer requestId;
    private String taskType;
    private List<Attachment> attachments;

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
