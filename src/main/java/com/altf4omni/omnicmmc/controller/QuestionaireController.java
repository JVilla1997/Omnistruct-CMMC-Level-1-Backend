package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.dto.QuestionCreationRequest;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import groovy.util.logging.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class QuestionaireController {
    private final QuestionaireService questionaireService;

    public QuestionaireController(QuestionaireService questionaireService) {
        this.questionaireService = questionaireService;
    }
    /**
     *
     * @param
     * @return
     */
    @GetMapping("/questions")
    public ResponseEntity<QuestionnaireResponse> getQuestionnaire() {
        var response = questionaireService.createQuestion(request);
        return ResponseEntity.ok(response);
    }
}