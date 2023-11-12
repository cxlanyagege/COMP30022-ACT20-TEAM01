package it.project.application.vo;

import it.project.application.pojo.AAP;
import it.project.application.pojo.Request;
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
