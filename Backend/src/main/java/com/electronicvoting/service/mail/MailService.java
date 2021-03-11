package com.electronicvoting.service.mail;

import com.electronicvoting.domain.dto.Mail;

public interface MailService {
    void sendEmail(Mail mail);
}
