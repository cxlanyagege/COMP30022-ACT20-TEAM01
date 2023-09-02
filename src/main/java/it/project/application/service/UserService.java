package it.project.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.project.application.entity.Student;
import it.project.application.mapper.StudentMapper;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentMapper.findByName(username);
        if (student == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(student.getName())
                   .password(student.getPassword())
                   .roles("USER")
                   .build();
    }
}
