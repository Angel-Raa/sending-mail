package org.caja.ideal.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.caja.ideal.models.EmailFileDto;
import org.caja.ideal.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements IEmailService {
    @Value("${email.send.account}")
    private String emailUsername;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String[] toUser, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(emailUsername);
        mail.setTo(toUser);
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);
    }

    @Override
    public void sendEmailWithFile(EmailFileDto emailFileDto, File file) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail,true, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(emailUsername);
        mimeMessageHelper.setTo(emailFileDto.getToUser());
        mimeMessageHelper.setSubject(emailFileDto.getSubject());
        mimeMessageHelper.setText(emailFileDto.getMessage());
        mimeMessageHelper.addAttachment(file.getName(), file);
        javaMailSender.send(mail);
    }
}
