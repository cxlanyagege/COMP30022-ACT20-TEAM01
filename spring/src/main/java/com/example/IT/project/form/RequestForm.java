/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: the class used to store the details of a request submitted
 * in JSON file from client to server
 */

package com.example.it.project.form;

import com.example.it.project.pojo.Attachment;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class RequestForm {
    // from client to server
    private String description;
    private Integer requestId;
    private Integer studentId;
    private Integer subjectId;
    private Date submissionDate;
    private String requestType;
    private String requestName;
    private String taskType;
    private String workType;
    private List<Attachment> attachments;
    private List<String> teammates;
}
