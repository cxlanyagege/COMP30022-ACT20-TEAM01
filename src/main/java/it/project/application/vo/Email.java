/**
 * Class Name: Email
 * Description: Email callback handler
 * 
 * Author: Dennis Wang
 * Date: 2023/10/19
 */

package it.project.application.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String recipient;
    private String msgBody;
    private String subject;
}
