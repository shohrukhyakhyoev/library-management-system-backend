package com.iutlibrary.backend.email;

public interface EmailSender {
    void send(String to, String body, String subject);


}
