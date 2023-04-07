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
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
        Document policyDocument = new Document();
        PdfWriter.getInstance(policyDocument, pdfOutput);
        policyDocument.open();

        //Page Title
        Chunk titleChunk;
        titleChunk = new Chunk("Cyber-security Policy", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD|Font.UNDERLINE));
        Paragraph paragraph = new Paragraph(titleChunk);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        policyDocument.add(paragraph);

        /**
         * Each section here is static the company name is inserted
         */

        /**
         * ACCESS CONTROL (AC)
         */
        policyDocument.add(new Paragraph("\n"));
        policyDocument.add(new Paragraph("ACCESS CONTROL", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));
        //AC.L1-3.1.1 – AUTHORIZED ACCESS CONTROL
        policyDocument.add(new Paragraph("AC.L1-3.1.1 – AUTHORIZED ACCESS CONTROL", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + " will limit information system access to: Identified, authorized users, processes" +
                " acting on behalf of authorized users, and identified, authorized devices (including other information systems) that will connect to the system."));

        //AC.L1-3.1.2 – TRANSACTION & FUNCTION CONTROL
        policyDocument.add(new Paragraph("AC.L1-3.1.2 – TRANSACTION & FUNCTION CONTROL", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will limit information system access to the types of transactions and functions " +
                "that authorized users are permitted to execute."));

        //AC.L1-3.1.20 – EXTERNAL CONNECTIONS
        policyDocument.add(new Paragraph("AC.L1-3.1.20 – EXTERNAL CONNECTIONS", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will identify, verify, and control/limit connections to and use of external information systems."));

        //AC.L1-3.1.22 – CONTROL PUBLIC INFORMATION
        policyDocument.add(new Paragraph("AC.L1-3.1.22 – CONTROL PUBLIC INFORMATION", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will control information posted or processed on publicly accessible information " +
                "systems by identified, authorized individuals. Procedures are in place to ensure Federal Contact Information (FCI) is not posted or processed on publicly accessible " +
                "systems. Content on publicly accessible systems is reviewed prior to posting, and to ensure mechanisms are in place to address and remove improper posting of FCI."));

        /**
         * Identification and Authentication (IA)
         */
        policyDocument.add(new Paragraph("\n"));
        policyDocument.add(new Paragraph("Identification and Authentication", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));

        //IA.L1-3.5.1 – IDENTIFICATION
        policyDocument.add(new Paragraph("IA.L1-3.5.1 – IDENTIFICATION", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will identify information system users, processes acting on behalf of users, and devices."));

        //IA.L1-3.5.2 – AUTHENTICATION
        policyDocument.add(new Paragraph("IA.L1-3.5.2 – AUTHENTICATION", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will authenticate (or verify) the identities of those users, processes acting on behalf of users," +
                " and devices connecting to the system as a prerequisite to allowing access to organizational information systems."));

        /**
         * Media Protection (MP)
         */
        policyDocument.add(new Paragraph("\n"));
        policyDocument.add(new Paragraph("Media Protection", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));

        //MP.L1-3.8.3 – MEDIA DISPOSAL
        policyDocument.add(new Paragraph("MP.L1-3.8.3 – MEDIA DISPOSAL", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will sanitize or destroy information system media containing Federal Contract Information before " +
                "disposal or release for reuse."));

        /**
         * Physical Protection (PE)
         */
        policyDocument.add(new Paragraph("\n"));
        policyDocument.add(new Paragraph("Physical Protection", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));

        //PE.L1-3.10.1 – LIMIT PHYSICAL ACCESS
        policyDocument.add(new Paragraph("PE.L1-3.10.1 – LIMIT PHYSICAL ACCESS", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "   will limit physical access to organizational information systems, equipment," +
                " and the respective operating environments to authorized individuals."));

        //PE.L1-3.10.3 – ESCORT VISITORS
        policyDocument.add(new Paragraph("PE.L1-3.10.1 – LIMIT PHYSICAL ACCESS", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will escort visitors and monitor visitor activity."));

        //PE.L1-3.10.4 – PHYSICAL ACCESS LOGS
        policyDocument.add(new Paragraph("PE.L1-3.10.4 – PHYSICAL ACCESS LOGS", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + " will maintain audit logs of physical access."));

        //PE.L1-3.10.5 – MANAGE PHYSICAL ACCESS
        policyDocument.add(new Paragraph("PE.L1-3.10.4 – PHYSICAL ACCESS LOGS", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + " will identify, control and manage physical access devices."));

        /**
         * System and Communications Protection (SC)
         */
        policyDocument.add(new Paragraph("\n"));
        policyDocument.add(new Paragraph("System and Communications Protection (SC)", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));

        //SC.L1-3.13.1 – BOUNDARY PROTECTION
        policyDocument.add(new Paragraph("SC.L1-3.13.1 – BOUNDARY PROTECTION", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will monitor, control, and protect organizational communications (i.e., " +
                "information transmitted or received by organizational information systems) at the external boundaries and key" +
                "internal boundaries of the information systems."));

        //SC.L1-3.13.5 – PUBLIC-ACCESS SYSTEM SEPARATION
        policyDocument.add(new Paragraph("SC.L1-3.13.5 – PUBLIC-ACCESS SYSTEM SEPARATION", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will implement subnetworks for publicly accessible system components that are physically " +
                "or logically separated from internal networks."));

        /**
         * System and Information Integrity (SI)
         */

        policyDocument.add(new Paragraph("\n"));
        policyDocument.add(new Paragraph("System and Information Integrity (SI)", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));


        //SI.L1-3.14.1 – FLAW REMEDIATION
        policyDocument.add(new Paragraph("SI.L1-3.14.1 – FLAW REMEDIATION", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will identify, report, and correct information and information system flaws in a timely manner." +
                " The time frame within which to identify, report, and correct information system flaws is specified."));

        //SI.L1-3.14.2 – MALICIOUS CODE PROTECTION
        policyDocument.add(new Paragraph("SI.L1-3.14.2 – MALICIOUS CODE PROTECTION", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will provide protection from malicious code at appropriately designated locations within the organizational information systems."));

        //SI.L1-3.14.4 – UPDATE MALICIOUS CODE PROTECTION
        policyDocument.add(new Paragraph("SI.L1-3.14.4 – UPDATE MALICIOUS CODE PROTECTION", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "   will update malicious code protection mechanisms when new releases are available."));

        //SI.L1-3.14.5 – SYSTEM & FILE SCANNING
        policyDocument.add(new Paragraph("SI.L1-3.14.5 – SYSTEM & FILE SCANNING", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
        policyDocument.add(new Paragraph("The " + answerRequestDto.getCompanyName() + "  will perform periodic scans of the information system and real-time scans of files from external " +
                "sources as files are downloaded, opened, or executed. The frequency at which these scans occur is defined."));


        policyDocument.close();
        byte[] pdfData = pdfOutput.toByteArray();
        ByteArrayResource byteResource= new ByteArrayResource(pdfData);

        HttpHeaders pdfHeader = new HttpHeaders();
        pdfHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=policy.pdf");

        return ResponseEntity.ok().headers(pdfHeader).contentType(MediaType.APPLICATION_PDF).body(byteResource);
    }
}
