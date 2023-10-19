/**
 * Class Name: RequestFrom
 * 
 * Description: The class used to store the details of a request 
 *              submitted in JSON file from client to server
 * 
 * Author: Dennis Wang & He Shen
 * 
 * Date: 2023/10/20
 */

package it.project.application.form;

import lombok.Data;

import java.sql.Date;
import java.util.List;

import it.project.application.pojo.Attachment;

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
    private String workType;
    private List<Attachment> attachments;
    private List<String> teammates;
}
