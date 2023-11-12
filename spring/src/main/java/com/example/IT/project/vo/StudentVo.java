package com.example.it.project.vo;

import com.example.it.project.pojo.AAP;
import com.example.it.project.pojo.Request;
import lombok.Data;

import java.util.List;

@Data
public class StudentVo {
    private Integer studentId;
    private String name;
    private String email;
    private boolean createRequest;
    private boolean deleteRequest;
    private boolean processRequest;
    private AAP aapAttachment;
    private List<Request> requestHistory;
}
