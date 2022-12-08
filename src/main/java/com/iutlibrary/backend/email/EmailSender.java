package com.iutlibrary.backend.email;

/**
 * Interface with a single function that sends an email to the given
 * address with all necessary info like message and topic of it.
 */
public interface EmailSender {
    /**
     * Sends message to the provided email address.
     *
     * @param to a string value representing a receiver's mail address.
     * @param body a string value representing message itself.
     * @param subject a string value representing topic of a message.
     */
    void send(String to, String body, String subject);


}
