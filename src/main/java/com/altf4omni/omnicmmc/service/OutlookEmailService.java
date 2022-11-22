package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public class OutlookEmailService implements EmailService {
    @Override
    public String sendMailWithAttachment(EmailDTO details) {
        return null;
    }
}
