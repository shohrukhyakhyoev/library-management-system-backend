package com.iutlibrary.backend.email;

import com.iutlibrary.backend.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service that actually configs all settings to send an email.
 * It extends EmailSender class. In addition, we create instance
 * of JavaMailSender class which provides us an abstraction in
 * sending email. We just call send function from our EmailSender
 * interface. JavaMailSender will take care of the low
 * level stuff.
 */
@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    /**
     * @field mailSender instance of JavaMailSender that actaully sends the mail and
     * is responsible for all low level stuff occurring while sending the email.
     */
    private final JavaMailSender mailSender;


    /**
     * Sends message to the provided email address.
     * Throws an exception if any problem occurs while sending.
     *
     * @param toEmail a string value representing a receiver's mail address.
     * @param body a string value representing message itself.
     * @param subject a string value representing topic of a message.
     * @throws ApiRequestException
     *
     */
    @Override
    public void send(String toEmail, String body, String subject) {

        try{
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("noreplyinhalibrary@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);

            mailSender.send(message);
        } catch (MailSendException e){
            throw new ApiRequestException("Message has not been sent successfully!");
        }
    }
}
