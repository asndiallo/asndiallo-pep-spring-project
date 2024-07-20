package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

/**
 * Service class for the Account entity to handle business logic.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * Constructs a new AccountService with the given account repository.
     * 
     * @param accountRepository the account repository
     */
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Registers a new account.
     * 
     * @param account the account to register
     * @return the registered account
     * @throws IllegalArgumentException if the username is blank or the password is
     *                                  too short
     * @throws IllegalStateException    if the username already exists
     */
    public Account registerAccount(Account account) {
        if (account.getUsername() == null || account.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new IllegalStateException("Username already exists");
        }
        return accountRepository.save(account);
    }
}
