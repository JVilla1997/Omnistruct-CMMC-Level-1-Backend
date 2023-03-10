package com.altf4omni.omnicmmc.enumeration;

import lombok.Getter;

public enum PassFailResult {
    PASS("Pass"), FAIL("Fail");
    /**
     * value will not be changed all that much
     * we know it will be pass or fail.
     * used a Getter that creates get() for label
     */
    @Getter
    private final String label;
     PassFailResult(String label){
        this.label = label;
    }
}
