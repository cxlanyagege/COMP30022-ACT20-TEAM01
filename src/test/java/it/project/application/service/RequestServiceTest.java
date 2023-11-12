/**
 * Class Name: RequestServiceTest
 * Description: Test for request service
 * 
 * Author: He Shen
 * Date: 2023/10/19
 */

package it.project.application.service;

import it.project.application.mapper.AttachmentMapper;
import it.project.application.mapper.RequestMapper;
import it.project.application.pojo.Attachment;
import it.project.application.pojo.Request;
import it.project.application.service.Impl.RequestServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RequestServiceTest {
    
    @MockBean
    private RequestMapper requestMapper;

    @MockBean
    private AttachmentMapper attachmentMapper;

    @Autowired
    private RequestServiceImpl requestService;

    private Request request;
    private Attachment attachment1, attachment2;
    private List<Attachment> attachments;

    @BeforeEach
    public void setup() {
        request = new Request();
        request.setRequestId(0);
        request.setDescription("This is a sample description");
        request.setStatus("WAITING");
        request.setSubmissionDate(Date.valueOf("2023-09-01"));
        request.setStudentId(0);
        request.setSubjectId(1);
        request.setRequestType("Assignment");
        request.setTaskType("Individual");
        request.setWorkType("Extension");
        request.setRequestName("Sample Request");

        attachment1 = new Attachment();
        attachment1.setUrl("/upload/file1.pdf");
        attachment1.setRequestId(0);

        attachment2 = new Attachment();
        attachment2.setUrl("/upload/file2.pdf");
        attachment2.setRequestId(0);

        attachments = Arrays.asList(attachment1, attachment2);
    }

    @Test
    void testSaveRequest() {
        // Arrange
        when(requestMapper.insert(request)).thenReturn(1);

        // Act
        requestService.saveMain(request, null);

        // Verify
        verify(requestMapper).insert(request);
    }

    @Test
    void testSaveWithAttachmentsRequest() {
        // Arrange
        when(requestMapper.insert(request)).thenReturn(1);
        when(attachmentMapper.insert(attachment1)).thenReturn(1);
        when(attachmentMapper.insert(attachment2)).thenReturn(1);

        // Act
        requestService.saveMain(request, attachments);

        // Verify
        verify(requestMapper).insert(request);
        verify(attachmentMapper).insert(attachment1);
        verify(attachmentMapper).insert(attachment2);
    }

    @Test
    void testGetRequest() {
        // Arrange
        when(requestMapper.selectById(0)).thenReturn(request);

        // Act
        Request result = requestService.getById(0);

        // Assert
        verify(requestMapper).selectById(0);
        assertEquals(request, result);
    }
}
