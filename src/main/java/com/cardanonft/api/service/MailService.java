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
            String htmlContent =  "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "   <meta name=\"\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>email</title>\n" +
                    "    <style>\n" +
                    "        @import url(\"https://fonts.googleapis.com/css2?family=Montserrat:wght@500;700&family=Noto+Sans:wght@400;700&display=swap\");\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"emainlWrap\" style = \"margin: 0;list-style: none;-webkit-box-sizing: border-box;box-sizing: border-box;text-decoration: none;font-style: normal;white-space: normal;word-break: keep-all;vertical-align: top;text-align: center;letter-spacing: -0.025em;padding: 20px 0;width: 100%;max-width: 100%;\">\n" +
                    "        <div class=\"contentsWrap\" style=\"background: #fff;-webkit-box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);height: fit-content;border: solid 10px #81B49E;padding: 20px 40px;position: relative;display: inline-block\">\n" +
                    "            <div class=\"logo\" style=\"width: 40%;\"><a href=\"https://cardanovillage.io/\" target=\"_blank\" style=\"display: inline-block;width: 100%;height: fit-content;\"><img src=\"https://village-image.s3.ap-northeast-2.amazonaws.com/email/emailLogo.png\" alt=\"cardano village\" name=\"cardano village\" style=\"max-width: 100%;min-width: 200px;\"></a></div>\n" +
                    "            <div class=\"codeWrap\" style=\"width: 100%;text-align: center;margin-top: 30px;display: inline-block;\">\n" +
                    "                <div class=\"codeImg\" style=\"max-width: 60%;width: 100%;display: inline-block;\">\n" +
                    "                    <img style=\"width: 100%;\" src=\"https://village-image.s3.ap-northeast-2.amazonaws.com/email/emailImg.jpg\" alt=\"Authentication Code\">\n" +
                    "                </div>\n" +
                    "                <br>\n" +
                    "                <div class=\"codeBox\" style=\"background: #E0EFE8;width: 100%;display: inline-block;padding: 20px 0;border-radius: 15px;margin-top: 10px;\">\n" +
                    "                    <div class=\"boxTitle\" style=\"font-size: 20px;color: #81B49E;font-weight: 700;font-family: \"Montserrat\", sans-serif;margin-bottom: 10px;padding: 0 20px;\">Authentication Code</div>\n" +
                    "                    <div class=\"code\" style=\"font-size: 30px;color: #222;font-weight: 700;font-family: \"Montserrat\", sans-serif;padding: 0 20px;\">"+authCode+"</div>\n" +
                    "                </div>\n" +
                    "                <div class=\"codeText\" style=\"font-size: 16px;color: #999;font-family: \"Noto Sans\", sans-serif;margin-top: 20px;margin-bottom: 100px;width: 100%;word-break: keep-all;\">Please enter the number above on the Cardano Village website.</div>\n" +
                    "            </div>\n" +
                    "            <div class=\"copyWrap\" style=\"width: 100%;font-size: 14px;color: #fff;font-family: \"Noto Sans\", sans-serif;background: #81B49E;margin-bottom: -10px;text-align: center;position: absolute;bottom: 0;left: 0;padding: 20px 0;\">\n" +
                    "                Copyright ⓒ 2021 Cardanian.kr All rights reserved.\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "</body></html>";

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
