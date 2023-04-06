package com.altf4omni.omnicmmc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionPostResponse {
    private boolean success;
    private Integer sectionID;
}
