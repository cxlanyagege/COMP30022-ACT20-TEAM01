/**
 * Class Name: IEmailService
 * 
 * Description: Email service interface used to send email to users
 * 
 * Author: Dennis Wang
 * 
 * Date: 2023/10/20
 */

package it.project.application.service;

import it.project.application.vo.Email;

public interface IEmailService {
    void sendSimpleMail(Email details);
}
