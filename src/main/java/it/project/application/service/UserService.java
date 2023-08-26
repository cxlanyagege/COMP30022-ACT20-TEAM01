package it.project.application.service;

import it.project.application.entity.Subject;
import it.project.application.entity.User;
import it.project.application.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // Authenticate user for log in purpose
    public User authenticate(String username, String password) {
        User user = userMapper.findByUsername(username);

        if (user != null) {
            // TODO: Encrypted password comparison
            if (Objects.equals(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    // Get enrolled subjects
    public List<Subject> getSubjectsForUser(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user.getSubjects();
    }

}
