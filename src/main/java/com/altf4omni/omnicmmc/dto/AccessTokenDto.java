package com.altf4omni.omnicmmc.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
    private int expires_in;
    private String token;
}
