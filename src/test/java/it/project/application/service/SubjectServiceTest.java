/**
 * Class Name: SubjectServiceTest
 * Description: Test for subject service
 * 
 * Author: He Shen
 * Date: 2023/10/19
 */

package it.project.application.service;

import it.project.application.mapper.SubjectMapper;
import it.project.application.pojo.Subject;
import it.project.application.service.Impl.SubjectServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SubjectServiceTest {

    @MockBean
    private SubjectMapper subjectMapper;

    @Autowired
    private SubjectServiceImpl subjectService;

    private Subject subject;

    @BeforeEach
    public void setup() {
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setSubjectName("Sample Subject");
    }

    @Test
    void testSaveSubject() {
        // Arrange
        when(subjectMapper.insert(subject)).thenReturn(1);

        // Act
        subjectService.save(subject);

        // Verify
        verify(subjectMapper).insert(subject);
    }

    @Test
    void testGetSubject() {
        // Arrange
        when(subjectMapper.selectById(1L)).thenReturn(subject);

        // Act
        Subject result = subjectService.getById(1L);

        // Assert
        verify(subjectMapper).selectById(1L);
        assertEquals(subject, result);
    }
}
