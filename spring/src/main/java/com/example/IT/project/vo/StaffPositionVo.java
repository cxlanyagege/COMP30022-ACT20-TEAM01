package com.example.it.project.vo;

import lombok.Data;

@Data
public class StaffPositionVo {
    private Integer staffId;
    private Integer subjectId;
    private String staffRole;
    private boolean assignmentRequest;
    private boolean examRequest;
    private boolean quizRequest;
    private boolean personalRequest;
    private boolean othersRequest;
    private String name;
    private String email;
}
