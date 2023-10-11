/**
 * Class Name: Student
 * Description: Entity for student
 * 
 * Author: He Shen
 * Date: 2023/10/9
 */

package it.project.application.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("student")
public class Student {

    @TableId
    private Long student_id;
    private String name;
    private String email;

    public Student(Long id, String name, String email) {
        this.student_id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return student_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
