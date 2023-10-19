/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: email is sent after several steps, so in the vo package
 */

package com.example.it.project.vo;

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
