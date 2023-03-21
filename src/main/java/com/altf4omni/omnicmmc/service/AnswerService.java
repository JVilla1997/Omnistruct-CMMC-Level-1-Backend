package com.altf4omni.omnicmmc.service;

import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerService answerService;

    public AnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public AnswerService getAnswerService() {
        return answerService;
    }
}
