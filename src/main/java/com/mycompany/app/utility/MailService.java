package com.mycompany.app.utility;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Properties;

@Log4j2
@RequiredArgsConstructor
public class MailService {
    private final static String HOST_ADDRESS = "kopytko@gmail.com";
    private final Properties properties;

    @WithSpan
    public void sendMail(String address, String message) {
        Session session = Session.getDefaultInstance(properties);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(HOST_ADDRESS));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(address));
            msg.setSubject("Order details");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(message, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            msg.setContent(multipart);
            Transport.send(msg);
            log.info("Mail sent: " + address);
        } catch (Exception e) {
            log.error("Sending mail failed: " + address, e);
        }
    }
}
