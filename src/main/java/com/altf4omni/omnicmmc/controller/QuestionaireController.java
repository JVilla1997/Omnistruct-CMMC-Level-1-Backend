package com.altf4omni.omnicmmc.controller;


import com.altf4omni.omnicmmc.dto.AnswerRequestDto;
import com.altf4omni.omnicmmc.dto.QuestionSectionDto;
import com.altf4omni.omnicmmc.dto.QuestionDto;
import com.altf4omni.omnicmmc.dto.ExtendedQuestionDto;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.dto.QuestionDeleteResponse;
import com.altf4omni.omnicmmc.dto.QuestionPostResponse;
import com.altf4omni.omnicmmc.dto.QuestionUpdateResponse;
import com.altf4omni.omnicmmc.dto.SectionDeleteResponse;
import com.altf4omni.omnicmmc.dto.SectionPostResponse;
import com.altf4omni.omnicmmc.dto.SectionUpdateResponse;
import com.altf4omni.omnicmmc.dto.ExtendedQuestionDeleteResponse;
import com.altf4omni.omnicmmc.dto.ExtendedQuestionPostResponse;
import com.altf4omni.omnicmmc.dto.ExtendedQuestionUpdateResponse;
import com.altf4omni.omnicmmc.entity.QuestionSection;
import com.altf4omni.omnicmmc.entity.Question;
import com.altf4omni.omnicmmc.entity.ExtendedQuestion;
import com.altf4omni.omnicmmc.service.AnswerService;
import com.altf4omni.omnicmmc.service.PolicyService;
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

    private final PolicyService policyService;

    public QuestionaireController(QuestionaireService questionaireService, AnswerService answerService, PolicyService policyService) {
        this.questionaireService = questionaireService;
        this.answerService = answerService;
        this.policyService = policyService;
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
     * Get the PDF and response
     * @return {@Link ByteArrayResource}
     */
    @PostMapping("/answer")
    public ResponseEntity<ByteArrayResource> createAnswerObject(@RequestBody AnswerRequestDto answerRequestDto) throws DocumentException, IOException {
        // Call the answerService to process the PDF
        var pdf = answerService.answerRequestList(answerRequestDto);

        //HTTP Header for pdf generator
        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=assessment.pdf");

        // Return the response
        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(pdf.getBody());
    }
    /**
     * Get the Policy PDF and response.
     * This PDF is slightly different from the other one
     * as this one tells the user what their policies should
     * look like.
     * @return {ByteArrayResource}
     */
    @PostMapping("/policy")
    public ResponseEntity<ByteArrayResource> createPolicyObject(@RequestBody AnswerRequestDto answerRequestDto) throws DocumentException, IOException {
        // Call the answerService to process the PDF
        var pdf = policyService.createPolicyList(answerRequestDto);

        //HTTP Header for pdf generator
        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        // Return the response
        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(pdf.getBody());
    }

    /**
     * POST Endpoint for section
     */
    @PostMapping("/section")
    public ResponseEntity<SectionPostResponse> createSection(@RequestBody QuestionSectionDto sectionToCreate) {
        if (sectionToCreate.getSectionName() == null || sectionToCreate.getSectionName() == "") {
            return ResponseEntity.badRequest().build();
        }
        var section = new QuestionSection(null, sectionToCreate.getSectionName(), sectionToCreate.getSequenceNumber(), null);
        var createdSectionID = questionaireService.createSection(section);
        var response = new SectionPostResponse(true, createdSectionID);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE Endpoint for section
     */
    @DeleteMapping("/section")
    public ResponseEntity<SectionDeleteResponse> deleteSection(@RequestBody QuestionSectionDto sectionToDelete) {
        if (sectionToDelete.getSectionID() == null) {
            return ResponseEntity.badRequest().build();
        }
        var result = questionaireService.deleteSection(sectionToDelete.getSectionID());
        if(!result) {
            return ResponseEntity.notFound().build();
        }
        var response = new SectionDeleteResponse(true);
        return ResponseEntity.ok(response);
    }

    /**
     * PUT Endpoint for section
     */
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
     * POST Endpoint for question
     */
    @PostMapping("/question")
    public ResponseEntity<QuestionPostResponse> createQuestion(@RequestBody QuestionDto questionToCreate) {
        if (questionToCreate.getSectionID() == null) {
            return ResponseEntity.badRequest().build();
        }
        var question = new Question(null, questionToCreate.getPrompt(), questionToCreate.getDescription(), questionToCreate.getSequenceNumber(), questionToCreate.getFlag(), null, questionToCreate.getSectionID());
        var createdQuestionID = questionaireService.createQuestion(question);
        var response = new QuestionPostResponse(true, createdQuestionID);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE Endpoint for question
     */
    @DeleteMapping("/question")
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(@RequestBody QuestionDto questionToDelete) {
        if (questionToDelete.getQuestionID() == null) {
            return ResponseEntity.badRequest().build();
        }

        var result = questionaireService.deleteQuestion(questionToDelete.getQuestionID());
        if(!result) {
            return ResponseEntity.notFound().build();
        }

        var response = new QuestionDeleteResponse(true);
        return ResponseEntity.ok(response);
    }

    /**
     * PUT Endpoint for question
     */
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

    /**
     * POST Endpoint for extended question
     */
    @PostMapping("/extended")
    public ResponseEntity<ExtendedQuestionPostResponse> createExtendedQuestion(@RequestBody ExtendedQuestionDto extendedQuestionToCreate) {
        if (extendedQuestionToCreate.getQuestionID() == null) {
            return ResponseEntity.badRequest().build();
        }
        var extendedQuestion = new ExtendedQuestion(null, extendedQuestionToCreate.getPrompt(), extendedQuestionToCreate.getDescription(), extendedQuestionToCreate.getSequenceNumber(), extendedQuestionToCreate.getQuestionID());
        var createdExtendedQuestionID = questionaireService.createExtendedQuestion(extendedQuestion);
        var response = new ExtendedQuestionPostResponse(true, createdExtendedQuestionID);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE Endpoint for extended question
     */
    @DeleteMapping("/extended")
    public ResponseEntity<ExtendedQuestionDeleteResponse> deleteExtendedQuestion(@RequestBody ExtendedQuestionDto extendedQuestionToDelete) {
        if (extendedQuestionToDelete.getExtendedQuestionID() == null) {
            return ResponseEntity.badRequest().build();
        }

        var result = questionaireService.deleteExtendedQuestion(extendedQuestionToDelete.getExtendedQuestionID());
        if(!result) {
            return ResponseEntity.notFound().build();
        }

        var response = new ExtendedQuestionDeleteResponse(true);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE Endpoint for question
     */
    @PutMapping("/extended")
    public ResponseEntity<ExtendedQuestionUpdateResponse> updateExtendedQuestion(@RequestBody ExtendedQuestionDto extendedQuestionToUpdate) {
        if (extendedQuestionToUpdate.getExtendedQuestionID() == null) {
            return ResponseEntity.badRequest().build();
        }
        var updatedExtendedQuestion = questionaireService.updateExtendedQuestion(extendedQuestionToUpdate);
        if (updatedExtendedQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        var response = new ExtendedQuestionUpdateResponse(true);
        return ResponseEntity.ok(response);
    }
}