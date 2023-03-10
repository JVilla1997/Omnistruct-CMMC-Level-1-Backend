package com.altf4omni.omnicmmc.dto;

import com.altf4omni.omnicmmc.enumeration.PassFailResult;
import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerDto {
    private String answer;
    private String description;
    private String prompt;
    private PassFailResult result; //the pass or fail result
    private List<ExtendedQuestionAnswerDto> extendedQuestionAnswers;
}
