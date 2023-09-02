package it.project.application.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("student")
public class Student {

    @TableId
    private int studentId;
    private String name;
    private String password;     // TODO: Encrypted password

    private List<Subject> subjects;

    public int getId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    // TODO: Need security considers on requesting password
    public String getPassword() {
        return password;
    }

    private void setId(int id) {
        this.studentId = id;
    }

    private void setUsername(String username) {
        this.name = username;
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
