/**
 * Class Name: Subject
 * Description: Entity for subjects
 * 
 * Author: He Shen
 * Date: 2023/8/16
 */

package it.project.application.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("subject")
public class Subject {

    @TableId
    private Integer id;

    private String name;

    private List<Student> users;

}
