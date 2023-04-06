package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.AnswerRequest;
import com.altf4omni.omnicmmc.dto.AnswerRequestDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PolicyService {
    public ResponseEntity<ByteArrayResource> createPolicyList(AnswerRequestDto answerRequestDto) throws DocumentException, IOException {
        final AnswerRequest answerRequest = new AnswerRequest();
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        Document policyDocument = new Document();
        PdfWriter.getInstance(policyDocument, pdfOutput);
        policyDocument.open();

        Chunk titleChunk;
        titleChunk = new Chunk("Cybersecurity Policy", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD|Font.UNDERLINE));
        Paragraph paragraph = new Paragraph(titleChunk);
        paragraph.setAlignment(Element.ALIGN_CENTER);


        policyDocument.add(new Paragraph("AC.L1-3.1.1 – AUTHORIZED ACCESS CONTROL", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The" + answerRequest.getCompanyName() + "will limit information system access to: Identified, authorized users, processes" +
                " acting on behalf of authorized users, and identified, authorized devices (including other information systems) that will connect to the system."));


        policyDocument.close();
        byte[] pdfData = pdfOutput.toByteArray();
        ByteArrayResource byteResource= new ByteArrayResource(pdfData);

        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(byteResource);
    }
}
