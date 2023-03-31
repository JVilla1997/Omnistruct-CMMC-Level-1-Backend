package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.AnswerRequest;
import com.altf4omni.omnicmmc.dto.AnswerRequestDto;
import com.altf4omni.omnicmmc.dto.ExtendedQuestionAnswerDto;
import com.altf4omni.omnicmmc.dto.QuestionAnswerDto;
import com.altf4omni.omnicmmc.enumeration.PassFailResult;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Service
public class AnswerService {
    public ResponseEntity<ByteArrayResource> answerRequestList(AnswerRequestDto answerRequestDto) throws DocumentException, IOException {
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, pdfOutput);
        document.open();

        //Title chunk for PDF
        Chunk chunk;
        Chunk extendedQuestionChunk;
        Chunk titleChunk = new Chunk("Policy Document", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD|Font.UNDERLINE));
        Paragraph paragraph = new Paragraph(titleChunk);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        //Adding Omnistruct logo
        String https = "https://raw.githubusercontent.com/jvillarante/Omnistruct-CMMC-Level-1-Web/CMMC-54-Touch-up-landing-page-and-results-page/main/src/image/Omni-Logo.png";
        Image image = Image.getInstance(new URL(https));
        image.setAlignment(Element.ALIGN_CENTER);
        image.scaleAbsoluteWidth(400f);
        image.scaleAbsoluteHeight(100f);
        document.add(image);

        //Adding title of document just after image
        document.add(paragraph);

        //Formatting pdf and JSON attributes added
        document.add(new Paragraph("\n"));
        for(AnswerRequest answerRequest:answerRequestDto.getSections()){
            //Section name: Formatted
            document.add(new Paragraph("Section: " + answerRequest.getSectionName(), new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));

            //For loop to go through  first main questions
            for (QuestionAnswerDto questionAnswerDto: answerRequest.getAnswerList()){
                //Answer: If question is skipped then it will say "skipped"
                if (Objects.equals(questionAnswerDto.getAnswer(), "")){
                    document.add(new Paragraph("Answer: Skipped"));
                } else {
                    document.add(new Paragraph("Answer: " + questionAnswerDto.getAnswer()));
                }

                //Description: If there is a description avaible then it show the description other wise it will show the descrition for the question
                if(questionAnswerDto.getDescription() == null){
                    document.add(new Paragraph("Description: No description avaiable "));
                } else {
                    document.add(new Paragraph("Description: " + questionAnswerDto.getDescription()));
                }
                //Prompt: Question displayed to user
                document.add(new Paragraph("Question: " + questionAnswerDto.getPrompt()));

                //Result: Using PassFailResult class I check the JSON file for a PASS or fail

                if(questionAnswerDto.getResult() == PassFailResult.PASS){
                    chunk = new Chunk("Result: Pass");
                    chunk.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.GREEN));
                } else {
                    chunk = new Chunk("Result: Fail");
                    chunk.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.RED));
                }
                document.add(new Paragraph(chunk));

                document.add(new Paragraph("\n"));

                //For loop to go through all the extended questions in a specific section
                for (ExtendedQuestionAnswerDto extendedQuestionAnswerDto: questionAnswerDto.getExtendedQuestionAnswers()){
                    document.add(new Paragraph("Question: " + extendedQuestionAnswerDto.getPrompt()));

                    if(extendedQuestionAnswerDto.getDescription() == null){
                        document.add(new Paragraph("Description: No description avaiable"));
                    } else {
                        document.add(new Paragraph("Description: " + extendedQuestionAnswerDto.getDescription()));
                    }

                    if (Objects.equals(extendedQuestionAnswerDto.getAnswer(), "")){
                        document.add(new Paragraph("Answer: Skipped"));
                    } else {
                        document.add(new Paragraph("Answer:" + extendedQuestionAnswerDto.getAnswer()));
                    }

                    if(extendedQuestionAnswerDto.getResult() == PassFailResult.PASS){
                        extendedQuestionChunk = new Chunk("Result: Pass");
                        extendedQuestionChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.GREEN));
                    } else {
                        extendedQuestionChunk = new Chunk("Result: Fail");
                        extendedQuestionChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.RED));
                    }

                    document.add(new Paragraph(extendedQuestionChunk));

                }
            }
        }

        document.add(new Paragraph("Disclaimer: This is an assessment only please contact Omnistruct directly for further evaluation and certification.", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD)));


        // Byte Array Resource and HTTP Headers
        document.close();
        byte[] pdfData = pdfOutput.toByteArray();
        ByteArrayResource byteResource= new ByteArrayResource(pdfData);

        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(byteResource);

    }
}
