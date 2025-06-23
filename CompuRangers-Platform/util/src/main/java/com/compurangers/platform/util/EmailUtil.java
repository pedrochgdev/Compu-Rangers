package com.compurangers.platform.util;

import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;

public class EmailUtil {
    private static final String fromEmail = Config.getEnv("EMAIL_SENDER");
    private static final String password = Config.getEnv("EMAIL_PASSWORD");

    public static Session getEmailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // ← esto es lo que falta

        return Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
    }


    public static String getSenderEmail() {
        return fromEmail;
    }
    
}
