/**
 * Class Name: StudentServiceTest
 * Description: Test for student service
 * 
 * Author: He Shen
 * Date: 2023/10/19
 */

package it.project.application.service;

import it.project.application.mapper.StudentMapper;
import it.project.application.pojo.Student;
import it.project.application.service.Impl.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class StudentServiceTest {

    @MockBean
    private StudentMapper studentMapper;

    @Autowired
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    public void setup() {
        student = new Student();
        student.setStudentId(1);
        student.setName("John Doe");
        student.setEmail("johndoe@student.unimelb.edu.au");
        student.setCreateRequest(true);
        student.setDeleteRequest(true);
        student.setProcessRequest(true);
    }

    @Test
    void testSaveStudent() {
        // Arrange
        when(studentMapper.insert(student)).thenReturn(1);

        // Act
        studentService.save(student);

        // Verify
        verify(studentMapper).insert(student);
    }

    @Test
    void testGetStudent() {
        // Arrange
        when(studentMapper.selectById(1L)).thenReturn(student);

        // Act
        Student result = studentService.getById(1L);

        // Assert
        verify(studentMapper).selectById(1L);
        assertEquals(student, result);
    }
}
