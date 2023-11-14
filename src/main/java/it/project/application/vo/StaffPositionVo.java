/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: package class to store info that frontend required on staff
 */

package it.project.application.vo;

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
