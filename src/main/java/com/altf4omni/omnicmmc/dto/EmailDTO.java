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
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
