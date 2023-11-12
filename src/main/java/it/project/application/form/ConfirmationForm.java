package it.project.application.form;

import lombok.Data;

@Data
public class ConfirmationForm {
    // confirmation message and status after tutor has processed a request
    private String status;
    private String message;
}
