package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.dto.AnswerRequest;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import groovy.util.logging.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

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

    /**
     * @return
     */
    @PostMapping(value = "answer", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> createAnswerObject(@RequestBody AnswerRequest answerRequest) throws DocumentException {
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, pdfOutput);
        document.open();

        Paragraph SectionName = new Paragraph(answerRequest.getSectionName());


        document.add(SectionName);

        document.close();
        byte[] pdfData = pdfOutput.toByteArray();
        ByteArrayResource byteResource= new ByteArrayResource(pdfData);

        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(byteResource);
    }

}