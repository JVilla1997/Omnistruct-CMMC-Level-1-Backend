package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.AnswerRequestDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class AnswerService {

    public ResponseEntity<ByteArrayResource> answerRequestList(AnswerRequestDto answerRequestDto) throws DocumentException {
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, pdfOutput);
        document.open();

        //Title chunk for PDF
        Chunk titleChunk = new Chunk("Policy Document", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        Paragraph paragraph = new Paragraph(titleChunk);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        // Byte Array Resource and HTTP Headers
        document.close();
        byte[] pdfData = pdfOutput.toByteArray();
        ByteArrayResource byteResource= new ByteArrayResource(pdfData);

        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(byteResource);

    }
}
