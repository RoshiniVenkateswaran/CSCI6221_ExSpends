package com.example.expensetracker.controller

import com.example.expensetracker.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
class TransactionController {

    @Autowired
    TransactionService transactionService

    @PostMapping("/income")
    ResponseEntity<?> addIncome(@RequestBody Map<String, Object> incomeDetails) {
        try {
            def response = transactionService.addIncome(incomeDetails)
            return ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "An error occurred while adding income: ${e.message}"])
        }
    }

    @PostMapping("/expense")
    ResponseEntity<?> addExpense(@RequestBody Map<String, Object> expenseDetails) {
        try {
            def response = transactionService.addExpense(expenseDetails)
            return ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "An error occurred while adding expense: ${e.message}"])
        }
    }
}
