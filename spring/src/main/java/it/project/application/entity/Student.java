/**
 * Class Name: Student
 * Description: Entity for student
 * 
 * Author: He Shen
 * Date: 2023/9/23
 * 
 * Note: Spring Security has not fully applied
 */

package it.project.application.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("student")
public class Student {

    @TableId
    private Long studentId;
    private String name;
    private String email;

    public Student(Long id, String name, String email) {
        this.studentId = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
