/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: email service interface used to send email to users
 */

package com.example.it.project.service;

import com.example.it.project.vo.Email;

public interface IEmailService {
    void sendSimpleMail(Email details);
}
