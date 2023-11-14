/**
 * Author: Dennis Wang & He Shen
 * Last modified data: 2023-11-14
 * Description: use to store info related to a staff's position in a subject that will be stored in database
 */

package it.project.application.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("is_responsible_for")
public class Position {
    // note that there's no table id specified here as this table is constructed
    // from the many-to-many relation, therefore has more than one primary key
    // hence we manually define a sql query to achieve this
    private Integer staffId;
    private Integer subjectId;
    private String staffRole;
    private boolean assignmentRequest;
    private boolean examRequest;
    private boolean quizRequest;
    private boolean personalRequest;
    private boolean othersRequest;
}
