package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.dto.AnswerRequest;
import com.altf4omni.omnicmmc.dto.ExtendedQuestionAnswerDto;
import com.altf4omni.omnicmmc.dto.QuestionAnswerDto;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.enumeration.PassFailResult;
import com.altf4omni.omnicmmc.service.QuestionaireService;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
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
     * Produce PDF
     * @return
     */
    @PostMapping(value = "answer", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> createAnswerObject(@RequestBody AnswerRequest answerRequest) throws DocumentException {
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, pdfOutput);
        document.open();

        //Title chunk for PDF
        Chunk titleChunk = new Chunk("Policy Document", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        Paragraph paragraph = new Paragraph(titleChunk);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        //Adding JSON attributes
        document.add(new Paragraph ("\n"));
        Paragraph SectionName = new Paragraph(answerRequest.getSectionName());
        document.add(new Paragraph ("Primary Questions"));
        document.add((new Paragraph("Section: " + SectionName)));
        for(QuestionAnswerDto questionAnswerDto: answerRequest.getAnswerList()){

            document.add(new Paragraph ("Answer: " + questionAnswerDto.getAnswer()));
            document.add(new Paragraph("Description: " + questionAnswerDto.getDescription()));
            document.add(new Paragraph("Prompt: " + questionAnswerDto.getPrompt()));

            if(questionAnswerDto.getResult() == PassFailResult.PASS){
                Chunk chunk = new Chunk("Result: Pass");
                chunk.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.GREEN));
                document.add(new Paragraph(chunk));
            } else {
                Chunk chunk = new Chunk("Result: Fail");
                chunk.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.RED));
                document.add(new Paragraph(chunk));
            }
            document.add(new Paragraph ("\n"));

            for(ExtendedQuestionAnswerDto extendedQuestionAnswerDto: questionAnswerDto.getExtendedQuestionAnswers()){
                document.add(new Paragraph ("Extended Questions"));
                document.add(new Paragraph ("Prompt: " + extendedQuestionAnswerDto.getPrompt()));
                document.add(new Paragraph ("Description: " + extendedQuestionAnswerDto.getDescription()));
                document.add(new Paragraph ("Answer: " + extendedQuestionAnswerDto.getAnswer()));

                if(extendedQuestionAnswerDto.getResult() == PassFailResult.PASS){
                    Chunk chunk = new Chunk("Result: Pass");
                    chunk.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.GREEN));
                    document.add(new Paragraph(chunk));
                } else {
                    Chunk chunk = new Chunk("Result: Fail");
                    chunk.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.RED));
                    document.add(new Paragraph(chunk));
                }




            }
        }
        document.close();
        byte[] pdfData = pdfOutput.toByteArray();
        ByteArrayResource byteResource= new ByteArrayResource(pdfData);

        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(byteResource);
    }

}