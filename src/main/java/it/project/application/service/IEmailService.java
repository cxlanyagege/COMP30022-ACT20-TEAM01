/**
 * Class Name: IEmailService
 * Description: Interface for Emailing Service
 * 
 * Author: Dennis Wang
 * Date: 2023/10/19
 */

package it.project.application.service;

import it.project.application.vo.Email;

public interface IEmailService {
    void sendSimpleMail(Email details);
}
