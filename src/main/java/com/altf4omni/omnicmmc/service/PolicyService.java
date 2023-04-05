package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.AnswerRequestDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PolicyService {
    public ResponseEntity<ByteArrayResource> createPolicyList(AnswerRequestDto answerRequestDto) throws DocumentException, IOException {
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        Document policyDocument = new Document();
        PdfWriter.getInstance(policyDocument, pdfOutput);
        policyDocument.open();

        policyDocument.close();
        byte[] pdfData = pdfOutput.toByteArray();
        ByteArrayResource byteResource= new ByteArrayResource(pdfData);

        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(byteResource);
    }
}
