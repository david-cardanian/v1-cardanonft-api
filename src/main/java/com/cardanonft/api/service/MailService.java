package com.cardanonft.api.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthService authService;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String cardanianMail;

    public void sendAuthCodeMail(String authCode, String requestUserEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        String resetPassword = authService.generateTmpPassword();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject("[Cardano Village] Auth Code");
            String htmlContent =  authCode;
            messageHelper.setText(htmlContent, true);
            messageHelper.setTo(requestUserEmail);
            messageHelper.setFrom(cardanianMail, "Cardano Village");
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            return;
        } catch (Throwable e) {
            e.printStackTrace();
            return;
        }
    }
}
