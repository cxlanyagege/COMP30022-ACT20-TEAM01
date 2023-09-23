/**
 * Class Name: Subject
 * Description: Entity for subjects
 * 
 * Author: He Shen
 * Date: 2023/9/23
 */

package it.project.application.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("subject")
public class Subject {

    @TableId
    private Long subjectId;
    private String subjectName;

    public Subject(Long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
