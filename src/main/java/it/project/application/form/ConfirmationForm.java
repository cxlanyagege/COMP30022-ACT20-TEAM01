/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: the class used to store the confirmation details once
 * staff has processed the request
 */

package it.project.application.form;

import lombok.Data;

@Data
public class ConfirmationForm {
    // confirmation message and status after tutor has processed a request
    private String status;
    private String message;
}
