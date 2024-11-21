package com.example.expensetracker.controller

import com.example.expensetracker.service.TransactionHistoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
class TransactionHistoryController {

    @Autowired
    TransactionHistoryService transactionHistoryService

    // Get Transaction History
    @GetMapping("/history")
    ResponseEntity<?> getAllTransactions() {
        try {
            def transactions = transactionHistoryService.getAllTransactions()
            return ResponseEntity.ok(transactions)
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "Error retrieving transaction history: ${e.message}"])
        }
    }

    // Update Income
    @PutMapping("/income/{incomeId}")
    ResponseEntity<?> updateIncome(@PathVariable("incomeId") Long incomeId, @RequestBody Map<String, Object> incomeDetails) {
        try {
            def response = transactionHistoryService.updateIncome(incomeId, incomeDetails)
            return ResponseEntity.ok(response)
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "Error updating income record: ${e.message}"])
        }
    }

    // Update Expense
    @PutMapping("/expense/{expenseId}")
    ResponseEntity<?> updateExpense(@PathVariable("expenseId") Long expenseId, @RequestBody Map<String, Object> expenseDetails) {
        try {
            def response = transactionHistoryService.updateExpense(expenseId, expenseDetails)
            return ResponseEntity.ok(response)
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "Error updating expense record: ${e.message}"])
        }
    }

    // Delete Income
    @DeleteMapping("/income/{incomeId}")
    ResponseEntity<?> deleteIncome(@PathVariable("incomeId") Long incomeId) {
        try {
            def response = transactionHistoryService.deleteIncome(incomeId)
            return ResponseEntity.ok(response)
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "Error deleting income record: ${e.message}"])
        }
    }

    // Delete Expense
    @DeleteMapping("/expense/{expenseId}")
    ResponseEntity<?> deleteExpense(@PathVariable("expenseId") Long expenseId) {
        try {
            def response = transactionHistoryService.deleteExpense(expenseId)
            return ResponseEntity.ok(response)
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "Error deleting expense record: ${e.message}"])
        }
    }
}
