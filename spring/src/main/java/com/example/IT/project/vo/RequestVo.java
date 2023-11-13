/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: sometimes request info needs to be sent to the frontend,
 * so this is created to store info required to send back to frontend
 */

package com.example.it.project.vo;

import com.example.it.project.pojo.Attachment;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class RequestVo {
    // from server to client
    // 返回的如果是一个class，springboot会自动把class转换成JSON格式
    private String description;
    private Integer studentId;
    private Date submissionDate;
    private String status;
    private String requestType;
    private String requestName;
    private Integer requestId;
    private String taskType;
    private String workType;
    private Integer subjectId;
    private List<Attachment> attachments;
    private List<String> teammates;
}
