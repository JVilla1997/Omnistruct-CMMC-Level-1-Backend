package com.altf4omni.omnicmmc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDTO {
    /**
     *
     */
    public static final String subject = "Questionnaire Answers";
    private String recipient;
    private String recipientEmail;
    private String msgBody;
    private String attachment;
}
