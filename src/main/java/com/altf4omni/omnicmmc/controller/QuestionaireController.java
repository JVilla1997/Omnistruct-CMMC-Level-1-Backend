package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.dto.AnswerRequest;
import com.altf4omni.omnicmmc.dto.ExtendedQuestionAnswerDto;
import com.altf4omni.omnicmmc.dto.QuestionAnswerDto;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.enumeration.PassFailResult;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.util.logging.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;

@Slf4j
@RestController
@CrossOrigin(origins = {"http://34.227.103.89:8080/questions"})
public class QuestionaireController {
    private final QuestionaireService questionaireService;

    public QuestionaireController(QuestionaireService questionaireService) {
        this.questionaireService = questionaireService;
    }
    /**
     * Get a questionnaire
     *
     * @return {@link QuestionnaireResponse}
     */
    @GetMapping("/questions")
    public ResponseEntity<QuestionnaireResponse> getQuestionnaire() {
        var response = questionaireService.getQuestionnaire();
        return ResponseEntity.ok(response);
    }
    /**
     * Produce PDF
     * @return
     */
    @PostMapping("/answer")
    public ResponseEntity<String> createAnswerObject(@RequestBody AnswerRequestDto answerRequestDto) {
        return ResponseEntity.ok("It works");
    }

}