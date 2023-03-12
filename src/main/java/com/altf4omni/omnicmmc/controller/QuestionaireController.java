package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.dto.ExtendedQuestionAnswerDto;
import com.altf4omni.omnicmmc.dto.AnswerRequest;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import groovy.util.logging.Slf4j;
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
    //private final AnswerRepository answerRepository;

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
    @PostMapping("/answer")
    public ResponseEntity<ExtendedQuestionAnswerDto> createAnswerObject(@RequestBody AnswerRequest answerRequest) throws DocumentException {
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, pdfOutput);
        document.open();

        Paragraph AnswerList = new Paragraph((Chunk) answerRequest.getAnswerList());
        Paragraph SectionName = new Paragraph(answerRequest.getSectionName());

        for(Element item: AnswerList ){
            document.add(new Paragraph((Chunk) item));
        }

        document.add(SectionName);

        document.close();
        byte[] pdfData = pdfOutput.toByteArray();
        ExtendedQuestionAnswerDto dto = new ExtendedQuestionAnswerDto();

        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.setContentType(MediaType.APPLICATION_PDF);
        pdfHeader.setContentDisposition(ContentDisposition.builder("attachment").filename("Policy Document.pdf").build());
        pdfHeader.setContentLength(pdfData.length);
        return new ResponseEntity<>(dto,pdfHeader, HttpStatus.OK);
    }

}