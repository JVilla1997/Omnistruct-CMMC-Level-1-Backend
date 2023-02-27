package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.dto.QuestionCreationRequest;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionaireController {
    private final QuestionaireService questionaireService;

    public QuestionaireController(QuestionaireService questionaireService) {
        this.questionaireService = questionaireService;
    }

    /**
     *
     * @param request Receives question and questionAnswer from the frontend and passess it through the method
     *                'createQuestion'
     * @return
     */
    @GetMapping("/questionQuestion")
    public ResponseEntity<String> createQuestion(
            @RequestBody QuestionCreationRequest request)  {
        var response = questionaireService.createQuestion(request);
        return ResponseEntity.ok(response);
    }
}