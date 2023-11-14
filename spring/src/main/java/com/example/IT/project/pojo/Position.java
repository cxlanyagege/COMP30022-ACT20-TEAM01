/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: use to store info related to a staff's position in a subject that will be stored in database
 */

package com.example.it.project.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("is_responsible_for")
public class Position {
    // note that there's no table id specified here as this table is constructed
    // from the many-to-many relation, therefore has more than one primary key
    private Integer staffId;
    private Integer subjectId;
    private String staffRole;
    private boolean assignmentRequest;
    private boolean examRequest;
    private boolean quizRequest;
    private boolean personalRequest;
    private boolean othersRequest;
}
