package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

/**
 * Service class for messages.
 */
@Service
public class MessageService {

    public final MessageRepository messageRepository;
    public final AccountRepository accountRepository;

    /**
     * Constructs a new MessageService with the given message and account
     * repositories.
     * 
     * @param messageRepository the message repository
     * @param accountRepository the account repository
     */
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new message.
     * 
     * @param message the message to create
     * @return the created message
     */
    public Message createMessage(Message message) {
        System.out.println("Creating message: " + message);
        System.out.println("Posted by: " + message.getPostedBy().longValue());
        if (message.getPostedBy() == null) {
            throw new IllegalArgumentException("Posted by must be provided");
        }
        if (message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            throw new IllegalArgumentException("Message text cannot be blank and must be under 255 characters");
        }
        if (!accountRepository.existsById(message.getPostedBy())) {
            throw new IllegalArgumentException("Posted by must refer to an existing user");
        }
        return messageRepository.save(message);
    }
}
