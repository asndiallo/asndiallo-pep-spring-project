package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * Controller class for social media functionality.
 */
@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    /**
     * Constructs a new SocialMediaController with the given account service.
     * 
     * @param accountService the account service
     * @param messageService the message service
     */
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Registers a new user account.
     * 
     * @param account the account to register
     * @return the registered account
     */
    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) {
        Account createdAccount = accountService.registerAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    /**
     * Logs in a user account.
     * 
     * @param account the account to log in
     * @return the logged in account
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Account account) {
        try {
            Account loggedInAccount = accountService.login(account.getUsername(), account.getPassword());
            return ResponseEntity.ok(loggedInAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    /**
     * Creates a new message.
     * 
     * @param message the message to create
     * @return the created message
     */
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok(createdMessage);
    }
}
