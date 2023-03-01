package com.altf4omni.omnicmmc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionnaireResponse {
    private List<QuestionSectionDto> questionSectionDtos;
}
