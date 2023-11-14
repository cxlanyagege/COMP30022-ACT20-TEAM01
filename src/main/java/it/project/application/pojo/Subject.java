/**
 * Class Name: Subject
 * Description: Use to store info related to subject that will be stored in database
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/11/12
 */

package it.project.application.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Subject")
public class Subject {
    @TableId("subject_id")
    private Integer subjectId;
    private String subjectName;
    // private String subjectCode;
    private boolean assignmentRequest;
    private boolean examRequest;
    private boolean quizRequest;
    private boolean personalRequest;
    private boolean othersRequest;
}
