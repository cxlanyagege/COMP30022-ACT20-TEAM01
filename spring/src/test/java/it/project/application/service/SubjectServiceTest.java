/**
 * Class Name: SubjectServiceTest
 * Description: Test for subject service
 * 
 * Author: He Shen
 * Date: 2023/9/24
 */

package it.project.application.service;

import it.project.application.entity.Subject;
import it.project.application.mapper.SubjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectMapper subjectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStoreWhenSubjectExists() {
        // Arrange
        Long id = (long) 7677778;
        String name = "AU-COURSE-TEMPLATE";
        Subject existingSubject = new Subject(id, name);

        // Act
        when(subjectMapper.findById(id)).thenReturn(existingSubject);
        subjectService.store(id, name);

        // Verify
        verify(subjectMapper, times(1)).findById(id);
        verify(subjectMapper, times(0)).insert(any(Subject.class));
    }

    @Test
    void testStoreWhenSubjectDoesNotExist() {
        // Arrange
        Long id = 1L;
        String name = "Math";

        // Act
        when(subjectMapper.findById(id)).thenReturn(null);
        subjectService.store(id, name);

        // Verify
        verify(subjectMapper, times(1)).findById(id);
        verify(subjectMapper, times(1)).insert(any(Subject.class));
    }

    @Test
    void testGetSubjectInfo() {
        // Arrange
        Long id = (long) 7677778;
        String name = "AU-COURSE-TEMPLATE";
        Subject existingSubject = new Subject(id, name);

        // Act
        when(subjectMapper.selectById(id)).thenReturn(existingSubject);
        Subject returnedSubject = subjectService.getSubjectInfo(id);

        // Assert
        assertEquals(existingSubject, returnedSubject);
        verify(subjectMapper, times(1)).selectById(id);
    }

    @Test
    void testGetSubjectInfoWhenSubjectDoesNotExist() {
        // Arrange
        Long id = 1L;

        // Act
        when(subjectMapper.selectById(id)).thenReturn(null);
        Subject returnedSubject = subjectService.getSubjectInfo(id);

        // Assert
        assertNull(returnedSubject);
        verify(subjectMapper, times(1)).selectById(id);
    }
}
