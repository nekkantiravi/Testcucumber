package com.macys.sdt.projects.SolutionDevelopment.SpecialCharactersSearchAndFilter.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by u063689 on 1/25/2018.
 */
public class Email {

    public void sendMail(String fromAddress, String toAddress, String bodyText, String subject) throws MessagingException {

        final String username = fromAddress;
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "APPMAIL.FEDERATED.FDS");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(bodyText);


            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
