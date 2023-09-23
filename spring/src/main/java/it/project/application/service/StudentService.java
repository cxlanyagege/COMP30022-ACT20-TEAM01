/**
 * Class Name: StudentService
 * Description: Service provided for manipulating student
 * 
 * Author: He Shen
 * Date: 2023/9/23
 */

package it.project.application.service;

import it.project.application.entity.Student;
import it.project.application.mapper.StudentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    // Authenticate user for log in purpose
    public Student authenticate(Long id, String name, String email) {

        // Find user name in database
        Student student = studentMapper.findById(id);

        // User who already registers StuRequestHub
        if (student != null) {
            return student;
        }

        // User who use for the first time
        Student newStudent = new Student(id, name, email);
        studentMapper.insert(newStudent);
        return newStudent;
    }
    
    // Get user info from user id
    public Student getUserInfo(Long studentId) {
        return studentMapper.selectById(studentId);
    }
}
