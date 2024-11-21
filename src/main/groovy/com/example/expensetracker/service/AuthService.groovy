package com.example.expensetracker.service

import com.example.expensetracker.repository.AccountRepository
import com.example.expensetracker.entity.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService {

    @Autowired
    AccountRepository accountRepository

    /**
     * Handles user registration.
     */
    def register(Account account) {
        try {
            if (accountRepository.findByUsername(account.username).isPresent()) {
                return [message: "Username already exists"]
            }
            accountRepository.save(account)
            return [message: "Registration successful"]
        } catch (Exception e) {
            e.printStackTrace()
            return [message: "An error occurred during registration: ${e.message}"]
        }
    }

    /**
     * Handles user login.
     */
    def login(String username, String password) {
        def accountOptional = accountRepository.findByUsername(username)
        if (!accountOptional.isPresent()) {
            return [message: "Invalid username or password", accountId: null]
        }

        def account = accountOptional.get()
        // Check if the provided password matches the account password
        if (account.password == password) { // Assuming plaintext password comparison
            return [message: "Login successful", accountId: account.accountId]
        } else {
            return [message: "Invalid username or password", accountId: null]
        }
    }
}
