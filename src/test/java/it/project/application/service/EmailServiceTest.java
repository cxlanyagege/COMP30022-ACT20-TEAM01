/**
 * Class Name: EmailServiceTest
 * Description: Test for email service
 * 
 * Author: He Shen
 * Date: 2023/10/19
 */

package it.project.application.service;

import it.project.application.service.Impl.EmailServiceImpl;
import it.project.application.vo.Email;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class EmailServiceTest {
    
    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailServiceImpl emailService;

    private Email details;

    @BeforeEach
    public void setup() {
        details = new Email();
        details.setMsgBody("This is a test mail from Spring Test");
        details.setRecipient("test@example.com");
        details.setSubject("Spring Test");
    }

    @Test
    void testSendEmail() {
        // No exception should be thrown
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

        // Act
        emailService.sendSimpleMail(details);

        // Verify
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
