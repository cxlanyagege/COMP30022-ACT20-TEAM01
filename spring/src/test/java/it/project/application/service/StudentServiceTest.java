package it.project.application.service;

import it.project.application.entity.Student;
import it.project.application.mapper.StudentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticateExistingStudentShouldReturnStudent() {
        // Arrange
        Long id = (long) 108998431;
        String name = "He Shen";
        String email = "heshen@student.unimelb.edu.au";
        Student existingStudent = new Student(id, name, email);

        when(studentMapper.findById(id)).thenReturn(existingStudent);

        // Act
        Student result = studentService.authenticate(id, name, email);

        // Assert
        assertEquals(existingStudent, result);
}

    @Test
    void authenticateNewStudentShouldReturnNewStudent() {
        // Arrange
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";

        when(studentMapper.findById(id)).thenReturn(null);

        // Act
        Student result = studentService.authenticate(id, name, email);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        verify(studentMapper).insert(any(Student.class));
    }

    @Test
    void getUserInfoShouldReturnStudent() {
        // Arrange
        Long id = (long) 108998431;
        Student student = new Student(id, "He Shen", "heshen@student.unimelb.edu.au");

        when(studentMapper.selectById(id)).thenReturn(student);

        // Act
        Student result = studentService.getUserInfo(id);

        // Assert
        assertEquals(student, result);
    }
}
