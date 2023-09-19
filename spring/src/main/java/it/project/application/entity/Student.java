/**
 * Class Name: Student
 * Description: Entity for student
 * 
 * Author: He Shen
 * Date: 2023/8/16
 * 
 * Note: Spring Security has not fully applied
 */

package it.project.application.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("student")
public class Student {

    @TableId
    private Long studentId;
    private String name;
    private String email;
    private String password;     // TODO: Encrypted password

    public Long getId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // TODO: Need security considers on requesting password
    public String getPassword() {
        return password;
    }

    private void setId(Long id) {
        this.studentId = id;
    }

    private void setUsername(String username) {
        this.name = username;
    }

    // TODO: Encrypted password reset
    private void setPassword(String password) {
        this.password = password;
    }

}
