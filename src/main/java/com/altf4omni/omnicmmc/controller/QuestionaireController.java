package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.dto.AnswerRequestDto;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.service.AnswerService;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import com.itextpdf.text.DocumentException;
import groovy.util.logging.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class QuestionaireController {
    private final QuestionaireService questionaireService;
    private AnswerService answerService;

    //private final AnswerRepository answerRepository;

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
     * @return
     */
    @PostMapping("/answer")
    public ResponseEntity<ByteArrayResource> createAnswerObject(@RequestBody AnswerRequestDto answerRequestDto) throws DocumentException {
        return answerService.answerRequestList(answerRequestDto);
    }

}