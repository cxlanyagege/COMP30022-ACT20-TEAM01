/**
 * Class Name: Email
 * 
 * Description: Email is sent after several steps, 
 *              so in the vo package
 * 
 * Author: Dennis Wang
 * 
 * Date: 2023/10/20
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
