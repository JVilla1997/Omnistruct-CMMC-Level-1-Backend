package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import groovy.util.logging.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QuestionaireController {
    private final QuestionaireService questionaireService;

    public QuestionaireController(QuestionaireService questionaireService) {
        this.questionaireService = questionaireService;
    }

    /**
     * Get a questionnaire
     * @return {@link QuestionnaireResponse}
     */
    @GetMapping("/questions")
    public ResponseEntity<QuestionnaireResponse> getQuestionnaire() {
        var response = questionaireService.getQuestionnaire();
        return ResponseEntity.ok(response);
    }
}