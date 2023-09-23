package com.example.IT.project.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("Request")
public class Request {
    // 和数据库相关
    @TableId("request_id") // Specify the primary key field
    private Integer requestId;
    private String description;
    private String status;
    private Date submissionDate;
    private Integer studentId;
    private String subjectId;
    private String requestType;
    private String taskType;
    private String requestName;
}
