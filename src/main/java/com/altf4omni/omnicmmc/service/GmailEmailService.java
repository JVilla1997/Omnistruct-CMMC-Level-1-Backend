package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.EmailDTO;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class GmailEmailService implements EmailService{
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    @Override
    public String sendMailWithAttachment(EmailDTO details) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(details.getRecipientEmail());
        msg.setSubject(EmailDTO.subject);
        msg.setText(
                "Hello, this is a test."
        );
        try {
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
        return msg.getText();
    }
}
