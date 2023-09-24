/**
 * Class Name: RequestVo
 * Description: Pojo for Request when callback
 * 
 * Author: Dennis Wang
 * Date: 2023/9/23
 */

package it.project.application.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

import it.project.application.pojo.Attachment;

@Data
public class RequestVo {
    // from server to client
    // if returns a class...
    // springboot will automatically translate class into JSON
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