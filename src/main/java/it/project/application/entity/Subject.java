package it.project.application.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("subject")
public class Subject {

    @TableId
    private Integer id;

    private String name;

    private List<User> users;

    // TODO: Regular getter and setter (for future use)

}
