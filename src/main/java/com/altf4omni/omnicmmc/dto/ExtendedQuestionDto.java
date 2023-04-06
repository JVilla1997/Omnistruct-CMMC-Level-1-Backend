package com.altf4omni.omnicmmc.dto;

import com.altf4omni.omnicmmc.enumeration.PassFailResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedQuestionDto {
    private Integer extendedQuestionID;
    private String prompt;
    private String description;
    private Integer sequenceNumber;
    private Integer questionID;

}
