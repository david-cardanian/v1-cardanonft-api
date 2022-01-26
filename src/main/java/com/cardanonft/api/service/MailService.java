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
                    "   <meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>email</title>\n" +
                    "    <style>\n" +
                    "        @import url(\"https://fonts.googleapis.com/css2?family=Montserrat:wght@500;700&family=Noto+Sans:wght@400;700&display=swap\");\n" +
                    "\n" +
                    "        .emainlWrap {\n" +
                    "            margin: 0;\n" +
                    "            list-style: none;\n" +
                    "            -webkit-box-sizing: border-box;\n" +
                    "            box-sizing: border-box;\n" +
                    "            text-decoration: none;\n" +
                    "            font-style: normal;\n" +
                    "            white-space: normal;\n" +
                    "            word-break: keep-all;\n" +
                    "            vertical-align: top;\n" +
                    "            text-align: center;\n" +
                    "            letter-spacing: -0.025em;\n" +
                    "            padding: 20px 0;\n" +
                    "            width: 100%;\n" +
                    "            max-width: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .contentsWrap {\n" +
                    "            background: #fff;\n" +
                    "            -webkit-box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);\n" +
                    "            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);\n" +
                    "            height: fit-content;\n" +
                    "            border: solid 10px #81B49E;\n" +
                    "            padding: 20px 40px;\n" +
                    "            position: relative;\n" +
                    "            display: inline-block;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .logo {\n" +
                    "            width: 40%;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .logo a {\n" +
                    "            display: inline-block;\n" +
                    "            width: 100%;\n" +
                    "            height: fit-content;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .logo a img {\n" +
                    "            max-width: 100%;\n" +
                    "            min-width: 200px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .codeWrap {\n" +
                    "            width: 100%;\n" +
                    "            text-align: center;\n" +
                    "            margin-top: 30px;\n" +
                    "            display: inline-block;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .codeImg {\n" +
                    "            max-width: 60%;\n" +
                    "            width: 100%;\n" +
                    "            display: inline-block;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .codeImg img {\n" +
                    "            width: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .codeBox {\n" +
                    "            background: #E0EFE8;\n" +
                    "            width: 100%;\n" +
                    "            display: inline-block;\n" +
                    "            padding: 20px 0;\n" +
                    "            border-radius: 15px;\n" +
                    "            margin-top: 10px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .boxTitle {\n" +
                    "            font-size: 20px;\n" +
                    "            color: #81B49E;\n" +
                    "            font-weight: 700;\n" +
                    "            font-family: \"Montserrat\", sans-serif;\n" +
                    "            margin-bottom: 10px;\n" +
                    "            padding: 0 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .code {\n" +
                    "            font-size: 30px;\n" +
                    "            color: #222;\n" +
                    "            font-weight: 700;\n" +
                    "            font-family: \"Montserrat\", sans-serif;\n" +
                    "            padding: 0 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .codeText {\n" +
                    "            font-size: 16px;\n" +
                    "            color: #999;\n" +
                    "            font-family: \"Noto Sans\", sans-serif;\n" +
                    "            margin-top: 20px;\n" +
                    "            margin-bottom: 100px;\n" +
                    "            width: 100%;\n" +
                    "            word-break: keep-all;\n" +
                    "        }\n" +
                    "\n" +
                    "        .emainlWrap .copyWrap {\n" +
                    "            width: 100%;\n" +
                    "            font-size: 14px;\n" +
                    "            color: #fff;\n" +
                    "            font-family: \"Noto Sans\", sans-serif;\n" +
                    "            background: #81B49E;\n" +
                    "            margin-bottom: -10px;\n" +
                    "            text-align: center;\n" +
                    "            position: absolute;\n" +
                    "            bottom: 0;\n" +
                    "            left: 0;\n" +
                    "            padding: 20px 0;\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"emainlWrap\">\n" +
                    "        <div class=\"contentsWrap\">\n" +
                    "            <div class=\"logo\"><a href=\"https://cardanovillage.io/\" target=\"_blank\"><img src=\"https://village-image.s3.ap-northeast-2.amazonaws.com/email/emailLogo.png\" alt=\"cardano village\" name=\"cardano village\"></a></div>\n" +
                    "            <div class=\"codeWrap\">\n" +
                    "                <div class=\"codeImg\">\n" +
                    "                    <img src=\"https://village-image.s3.ap-northeast-2.amazonaws.com/email/emailImg.jpg\" alt=\"Authentication Code\">\n" +
                    "                </div>\n" +
                    "                <br>\n" +
                    "                <div class=\"codeBox\">\n" +
                    "                    <div class=\"boxTitle\">Authentication Code</div>\n" +
                    "                    <div class=\"code\">"+authCode+"</div>\n" +
                    "                </div>\n" +
                    "                <div class=\"codeText\">Please enter the number above on the Cardano Village website.</div>\n" +
                    "            </div>\n" +
                    "            <div class=\"copyWrap\">\n" +
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
