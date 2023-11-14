/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: student mapper created to connect to JDBC automatically from relevant service
 */

package com.example.it.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.it.project.pojo.Student;

public interface StudentMapper extends BaseMapper<Student> {
}
