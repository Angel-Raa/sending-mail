package org.caja.ideal.service;

import jakarta.mail.MessagingException;
import org.caja.ideal.models.EmailFileDto;

import java.io.File;

public interface IEmailService {
    void sendEmail(String[] toUser, String subject, String message);
    void sendEmailWithFile(EmailFileDto EmailFileDto, File file ) throws MessagingException;

}
