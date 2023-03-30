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
    public QuestionaireController(QuestionaireService questionaireService, AnswerService answerService) {
        this.questionaireService = questionaireService;
        this.answerService = answerService;
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
     * Get the PDF
     * @return {@Link ByteArrayResource}
     */
    @PostMapping("/answer")
    public ResponseEntity<ByteArrayResource> createAnswerObject(@RequestBody AnswerRequestDto answerRequestDto) throws DocumentException {
        // Call the answerService to process the PDF
       var pdf = answerService.answerRequestList(answerRequestDto);
        // Return the response
        return ResponseEntity.ok(pdf).getBody();
    }

}