package com.compurangers.platform.service.utils;

import com.compurangers.platform.util.Config;
import com.compurangers.platform.util.EmailUtil;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailService {

    private void sendEmail(String toEmail, String subject, String body) {
        try {
            Session session = EmailUtil.getEmailSession();

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(EmailUtil.getSenderEmail()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendPasswordRecoveryEmail(String toEmail, String token) {
        String subject = "Recuperación de contraseña";
        String link =  Config.getEnv("LINK")+ "Auth/ResetPassword?token=" + token;
        String body = """
                      Hola, has solicitado restablecer tu contrase\u00f1a.
                      
                      Haz clic en el siguiente enlace para continuar:
                      """
                    + link + "\n\n"
                    + "Este enlace expirará en 5 minutos.";
        sendEmail(toEmail, subject, body);
    }
    
}
