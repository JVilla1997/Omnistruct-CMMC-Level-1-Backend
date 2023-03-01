package com.altf4omni.omnicmmc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedQuestionDto {
    private String prompt;
    private String description;
    private Integer sequenceNumber;
}
