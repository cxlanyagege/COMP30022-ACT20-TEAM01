/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: use to store info related to student that will be stored in database
 */

package com.example.it.project.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Student")
public class Student {
    @TableId("student_id")
    private Integer studentId;
    private String name;
    private String email;
    private boolean createRequest;
    private boolean deleteRequest;
    private boolean processRequest;
}
