/**
 * Class Name: Subject
 * Description: Entity for subjects
 * 
 * Author: He Shen
 * Date: 2023/10/9
 */

package it.project.application.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("subject")
public class Subject {

    @TableId
    private Long subject_id;
    private String subject_name;

    public Subject(Long id, String name) {
        this.subject_id = id;
        this.subject_name = name;
    }

    public Long getSubjectId() {
        return subject_id;
    }

    public String getSubjectName() {
        return subject_name;
    }
}
