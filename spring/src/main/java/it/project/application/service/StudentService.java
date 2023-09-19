/**
 * Class Name: StudentService
 * Description: Service provided for manipulating student
 * 
 * Author: He Shen
 * Date: 2023/8/16
 */

package it.project.application.service;

import it.project.application.entity.Subject;
import it.project.application.entity.Student;
import it.project.application.mapper.StudentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private StudentMapper userMapper;

    // Authenticate user for log in purpose
    public Student authenticate(String username, String password) {
        Student user = userMapper.findByName(username);

        if (user != null) {
            // TODO: Encrypted password comparison
            if (Objects.equals(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    
    // Get user info from user id
    public Student getUserInfo(Long studentId) {
        return userMapper.selectById(studentId);
    }
    

}
