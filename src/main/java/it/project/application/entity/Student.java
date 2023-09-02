package it.project.application.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("student")
public class Student {

    @TableId
    private Long id;
    private String username;
    private String password;     // TODO: Encrypted password

    private List<Subject> subjects;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    // TODO: Need security considers on requesting password
    public String getPassword() {
        return password;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    // TODO: Encrypted password reset
    private void setPassword(String password) {
        this.password = password;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
