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

    /**
     * Add Income
     */
    @PostMapping("/income")
    ResponseEntity<?> addIncome(@RequestBody Map<String, Object> incomeDetails) {
        try {
            def response = transactionService.addIncome(incomeDetails)
            if (response) {
                return ResponseEntity.status(HttpStatus.CREATED).body([
                    status : HttpStatus.CREATED.value(),
                    message: "Income added successfully",
                    data   : response
                ])
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body([
                    status : HttpStatus.BAD_REQUEST.value(),
                    message: "Failed to add income",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred while adding income: ${e.message}",
                data   : null
            ])
        }
    }

    /**
     * Add Expense
     */
    @PostMapping("/expense")
    ResponseEntity<?> addExpense(@RequestBody Map<String, Object> expenseDetails) {
        try {
            def response = transactionService.addExpense(expenseDetails)
            if (response) {
                return ResponseEntity.status(HttpStatus.CREATED).body([
                    status : HttpStatus.CREATED.value(),
                    message: "Expense added successfully",
                    data   : response
                ])
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body([
                    status : HttpStatus.BAD_REQUEST.value(),
                    message: "Failed to add expense",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred while adding expense: ${e.message}",
                data   : null
            ])
        }
    }
}
