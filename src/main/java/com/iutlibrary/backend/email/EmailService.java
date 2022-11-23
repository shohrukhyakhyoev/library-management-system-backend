package com.iutlibrary.backend.email;

import com.iutlibrary.backend.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;


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
