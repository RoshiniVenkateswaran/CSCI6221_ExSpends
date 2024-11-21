package com.example.expensetracker.controller

import com.example.expensetracker.entity.Account
import com.example.expensetracker.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController {

    @Autowired
    AuthService authService

    /**
     * Endpoint to handle user registration.
     */
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody Account account) {
        try {
            def response = authService.register(account)
            if (response?.message == "Registration successful") {
                return ResponseEntity.status(HttpStatus.CREATED).body([
                    status: HttpStatus.CREATED.value(),
                    message: response.message
                ])
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body([
                    status: HttpStatus.BAD_REQUEST.value(),
                    message: response?.message ?: "Registration failed"
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status: HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred: ${e.message}"
            ])
        }
    }

    /**
     * Endpoint to handle user login.
     */
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            def username = credentials.get("username")
            def password = credentials.get("password")

            def response = authService.login(username, password)
            if (response?.message == "Login successful") {
                return ResponseEntity.ok([
                    status: HttpStatus.OK.value(),
                    message: response.message,
                    accountId: response.accountId
                ])
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body([
                    status: HttpStatus.UNAUTHORIZED.value(),
                    message: response?.message ?: "Invalid credentials"
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status: HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred: ${e.message}"
            ])
        }
    }
}
