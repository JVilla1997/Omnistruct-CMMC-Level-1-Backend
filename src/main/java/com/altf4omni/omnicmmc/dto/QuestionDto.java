package com.altf4omni.omnicmmc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Integer questionID;
    private List<ExtendedQuestionDto> extendedQuestion;
    private String prompt;
    private String description;
    private Boolean flag;
    private Integer sequenceNumber;
}
