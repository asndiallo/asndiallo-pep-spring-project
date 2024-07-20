package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;

/**
 * Controller class for social media functionality.
 */
@RestController
public class SocialMediaController {

    private final AccountService accountService;

    /**
     * Constructs a new SocialMediaController with the given account service.
     * 
     * @param accountService the account service
     */
    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
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
}
