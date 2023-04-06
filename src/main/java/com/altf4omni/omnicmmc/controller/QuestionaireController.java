package com.altf4omni.omnicmmc.controller;


import com.altf4omni.omnicmmc.dto.AnswerRequestDto;
import com.altf4omni.omnicmmc.dto.QuestionSectionDto;
import com.altf4omni.omnicmmc.dto.QuestionDto;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.dto.QuestionDeleteResponse;
import com.altf4omni.omnicmmc.dto.QuestionPostResponse;
import com.altf4omni.omnicmmc.dto.QuestionUpdateResponse;
import com.altf4omni.omnicmmc.dto.SectionDeleteResponse;
import com.altf4omni.omnicmmc.dto.SectionPostResponse;
import com.altf4omni.omnicmmc.dto.SectionUpdateResponse;
import com.altf4omni.omnicmmc.entity.QuestionSection;
import com.altf4omni.omnicmmc.entity.Question;
import com.altf4omni.omnicmmc.entity.ExtendedQuestion;
import com.altf4omni.omnicmmc.service.AnswerService;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import com.itextpdf.text.DocumentException;
import groovy.util.logging.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin
//@CrossOrigin(origins = {"http://34.227.103.89:8080/questions"})
public class QuestionaireController {
    private final QuestionaireService questionaireService;

    private final AnswerService answerService;

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

    @PostMapping("/section")
    public ResponseEntity<SectionPostResponse> createSection(@RequestBody QuestionSectionDto sectionToCreate) {
        var section = new QuestionSection(null, sectionToCreate.getSectionName(), sectionToCreate.getSequenceNumber(), null);
        var createdSectionID = questionaireService.createSection(section);
        var response = new SectionPostResponse(true, createdSectionID);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/section")
    public ResponseEntity<SectionDeleteResponse> deleteSection(@RequestBody QuestionSectionDto sectionToDelete) {
        questionaireService.deleteSection(sectionToDelete.getSectionID());
        var response = new SectionDeleteResponse(true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/section")
    public ResponseEntity<SectionUpdateResponse> updateSection(@RequestBody QuestionSectionDto sectionToUpdate) {
        if (sectionToUpdate.getSectionID() == null) {
            return ResponseEntity.badRequest().build();
        }
        var updatedSection = questionaireService.updateSection(sectionToUpdate);
        if (updatedSection == null) {
            return ResponseEntity.notFound().build();
        }
        var response = new SectionUpdateResponse(true);
        return ResponseEntity.ok(response);
    }
    /**
     * Get the PDF and response
     * @return {@Link ByteArrayResource}
     */
    @PostMapping("/answer")
    public ResponseEntity<ByteArrayResource> createAnswerObject(@RequestBody AnswerRequestDto answerRequestDto) throws DocumentException, IOException {
        // Call the answerService to process the PDF
        var pdf = answerService.answerRequestList(answerRequestDto);

        //HTTP Header for pdf generator
        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        // Return the response
        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(pdf.getBody());
    }

    @PostMapping("/question")
    public ResponseEntity<QuestionPostResponse> createQuestion(@RequestBody QuestionDto questionToCreate) {
        var question = new Question(null, questionToCreate.getPrompt(), questionToCreate.getDescription(), questionToCreate.getSequenceNumber(), questionToCreate.getFlag(), null);
        var createdQuestionID = questionaireService.createQuestion(question);
        var response = new QuestionPostResponse(true, createdQuestionID);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/question")
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(@RequestBody QuestionDto questionToDelete) {
        questionaireService.deleteQuestion(questionToDelete.getQuestionID());
        var response = new QuestionDeleteResponse(true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/question")
    public ResponseEntity<QuestionUpdateResponse> updateQuestion(@RequestBody QuestionDto questionToUpdate) {
        if (questionToUpdate.getQuestionID() == null) {
            return ResponseEntity.badRequest().build();
        }
        var updatedQuestion = questionaireService.updateQuestion(questionToUpdate);
        if (updatedQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        var response = new QuestionUpdateResponse(true);
        return ResponseEntity.ok(response);
    }
}