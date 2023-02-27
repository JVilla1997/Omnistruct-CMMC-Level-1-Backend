package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.EmailDTO;

public interface EmailService {

    /**
     * Send simple email to a recipient
     *
     * @param details email details
     * @return String
     */
    String sendMailWithAttachment(EmailDTO details);


}
