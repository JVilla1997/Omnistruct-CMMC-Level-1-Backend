package com.altf4omni.omnicmmc.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequestDto {
    private List<AnswerRequest> sections;
    private String companyName;
}
