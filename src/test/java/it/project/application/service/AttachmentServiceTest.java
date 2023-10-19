/**
 * Class Name: AttachmentServiceTest
 * Description: Test for attachment service
 * 
 * Author: He Shen
 * Date: 2023/10/19
 */

package it.project.application.service;

import it.project.application.mapper.AttachmentMapper;
import it.project.application.pojo.Attachment;
import it.project.application.service.Impl.AttachmentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AttachmentServiceTest {

    @MockBean
    private AttachmentMapper attachmentMapper;

    @Autowired
    private AttachmentServiceImpl attachmentService;

    private Attachment attachment;

    @BeforeEach
    public void setup() {
        Attachment attachment = new Attachment();
        attachment.setAttachmentId(0);
        attachment.setRequestId(0);
        attachment.setUrl("/upload/Sample Attachment.pdf");
    }

    @Test
    void testSaveAttachment() {
        // Arrange
        when(attachmentMapper.insert(attachment)).thenReturn(1);

        // Act
        attachmentService.save(attachment);

        // Verify
        verify(attachmentMapper).insert(attachment);
    }

    @Test
    void testGetAttachment() {
        // Arrange
        when(attachmentMapper.selectById(0)).thenReturn(attachment);

        // Act
        Attachment result = attachmentService.getById(0);

        // Assert
        verify(attachmentMapper).selectById(0);
        assertEquals(attachment, result);
    }
    
}
