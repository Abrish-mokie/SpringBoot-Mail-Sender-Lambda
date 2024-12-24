package org.example.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.example.DTO.EmailVerificationBody;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final MustacheService mustacheService;

    public EmailService(JavaMailSender mailSender, MustacheService mustacheService) {
        this.mailSender = mailSender;
        this.mustacheService = mustacheService;
    }

    public void sendEmailWithHtml(EmailVerificationBody emailVerificationBody) throws Error, MessagingException, IOException {
        String html = htmlTemplate(emailVerificationBody.verificationToken());
        System.out.println("\n testing html" + emailVerificationBody);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);


        helper.setFrom(emailVerificationBody.fromEmail() + "@abrishmokie.com",emailVerificationBody.from());
        helper.setTo(emailVerificationBody.to());
        helper.setSubject(emailVerificationBody.subject());
        helper.setText(html,true);

        mailSender.send(message);

    }

    private String htmlTemplate(String tokenLink) throws IOException {
        Map<String,Object> context = new HashMap<>();
        context.put("tokenLink",tokenLink);
        return mustacheService.renderTemplate("EmailTokenVerification.mustache",context);

    }
}

