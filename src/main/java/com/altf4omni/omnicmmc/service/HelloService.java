package com.altf4omni.omnicmmc.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String appendBitch(String word) {
        return word + " CS190";
    }
}
