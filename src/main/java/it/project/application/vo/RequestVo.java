/**
 * Class Name: RequestVo
 * 
 * Description: Sometimes request info needs to be sent to 
 *              the frontend, so this is created to store 
 *              info required to send back to frontend
 * 
 * Author: Dennis Wang & He Shen
 * 
 * Date: 2023/10/20
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
    private Integer studentId;
    private Date submissionDate;
    private String status;
    private String requestType;
    private String requestName;
    private Integer requestId;
    private String taskType;
    private String workType;
    private List<Attachment> attachments;
    private List<String> teammates;
}
