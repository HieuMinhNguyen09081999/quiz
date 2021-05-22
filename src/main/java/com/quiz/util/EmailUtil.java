package com.quiz.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	private static String token;
	
	public static void sendEmail(String recipient, int mode) throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccount = "nmhieu981999@gmail.com";
        String password = "09081999";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, password);
            }
        });

        Message message = prepareMessage(session, myAccount, recipient, mode);

        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccount, String recipient, int mode) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            token = Math.round(Math.random() * 100000000) + "";
            message.setSubject("[Quiz Online] Forget password");
            if (mode == 1) {
                String htmlCode = "<h2>Forget password</h2>" +
                		"<p style='color: red'> Please enter code in 60s. </p>" +
                        "<h3 style='display: inline;'>Your code is : </h3>" +
                        "<i><mark>" + token + " </mark></i>";
                message.setContent(htmlCode, "text/html");
            }
            if (mode == 2) {
                String htmlCode = "<h2>Forget password</h2> <br />" +
                        "<h3>Your password is : </h3>" +
                        "<i><mark>" + token + " </mark></i>";
                message.setContent(htmlCode, "text/html");
            }
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getToken() {
        return token;
    }
}
