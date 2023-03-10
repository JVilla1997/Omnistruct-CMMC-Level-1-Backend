package com.altf4omni.omnicmmc.dto;

import com.altf4omni.omnicmmc.enumeration.PassFailResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedQuestionAnswerDto {
    private String prompt; //question
    private String description;
    private String answer; //this is the actual answer
    private PassFailResult result; //the pass or fail result
}
