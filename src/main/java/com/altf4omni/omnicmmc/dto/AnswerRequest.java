package com.altf4omni.omnicmmc.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequest {
    private List<QuestionAnswerDto> answerList;
    private String sectionName;
    private String sectionResult;
    private String companyName;
}
