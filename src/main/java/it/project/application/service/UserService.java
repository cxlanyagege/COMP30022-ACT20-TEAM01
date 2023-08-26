package it.project.application.service;

import it.project.application.entity.Subject;
import it.project.application.entity.User;
import it.project.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Authenticate user for log in purpose
    public User authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // TODO: Encrypted password comparison
            if (Objects.equals(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    // Get enrolled subjects
    public List<Subject> getSubjectsForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));
        return user.getSubjects();
    }

}
