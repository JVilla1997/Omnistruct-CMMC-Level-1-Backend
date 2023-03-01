package com.altf4omni.omnicmmc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSectionDto {
    private List<QuestionDto> questions;
    private String sectionName;
    private Integer sequenceNumber;
}
