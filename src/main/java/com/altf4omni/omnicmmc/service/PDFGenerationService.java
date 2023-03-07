package com.altf4omni.omnicmmc.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayOutputStream;

@Controller
public class PDFGenerationService {

    @GetMapping("/create-pdf")
    public ResponseEntity<byte[]> createPDF() throws DocumentException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document policyDocument = new Document();
        PdfWriter.getInstance(policyDocument,outputStream);

        policyDocument.open();
        policyDocument.add(new Paragraph("test"));
        policyDocument.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("policy", "policy.pdf");
        headers.setContentLength(outputStream.size());

        return new ResponseEntity<>(outputStream.toByteArray(),headers, HttpStatus.OK);
    }
}
