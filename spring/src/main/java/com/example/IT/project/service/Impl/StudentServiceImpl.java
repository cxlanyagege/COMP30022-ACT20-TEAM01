/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: student service do operations related to database
 */

package com.example.it.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.it.project.mapper.StudentMapper;
import com.example.it.project.pojo.Student;
import com.example.it.project.service.IStudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
}
