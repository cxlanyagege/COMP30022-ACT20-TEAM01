/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: use to store info related to a request that will be stored in database as well
 */

package com.example.it.project.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("Request")
public class Request {
    // class created to store information directly connected to database
    @TableId("request_id") // Specify the primary key field
    private Integer requestId;
    private String description;
    private String status;
    private Date submissionDate;
    private Integer studentId;
    private String subjectId;
    private String requestType;
    private String taskType;
    private String workType;
    private String requestName;
}
