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

    /**
     * Get Transaction History
     */
    @GetMapping("/history")
    ResponseEntity<?> getAllTransactions() {
        try {
            def transactions = transactionHistoryService.getAllTransactions()
            if (transactions) {
                return ResponseEntity.ok([
                    status : HttpStatus.OK.value(),
                    message: "Transaction history retrieved successfully",
                    data   : transactions
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "No transactions found",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "Error retrieving transaction history: ${e.message}",
                data   : null
            ])
        }
    }

    /**
     * Update Income
     */
    @PutMapping("/income/{incomeId}")
    ResponseEntity<?> updateIncome(@PathVariable("incomeId") Long incomeId, @RequestBody Map<String, Object> incomeDetails) {
        try {
            def updated = transactionHistoryService.updateIncome(incomeId, incomeDetails)
            if (updated) {
                return ResponseEntity.ok([
                    status : HttpStatus.OK.value(),
                    message: "Income updated successfully",
                    data   : updated
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "Income record not found",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "Error updating income record: ${e.message}",
                data   : null
            ])
        }
    }

    /**
     * Update Expense
     */
    @PutMapping("/expense/{expenseId}")
    ResponseEntity<?> updateExpense(@PathVariable("expenseId") Long expenseId, @RequestBody Map<String, Object> expenseDetails) {
        try {
            def updated = transactionHistoryService.updateExpense(expenseId, expenseDetails)
            if (updated) {
                return ResponseEntity.ok([
                    status : HttpStatus.OK.value(),
                    message: "Expense updated successfully",
                    data   : updated
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "Expense record not found",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "Error updating expense record: ${e.message}",
                data   : null
            ])
        }
    }

    /**
     * Delete Income
     */
    @DeleteMapping("/income/{incomeId}")
    ResponseEntity<?> deleteIncome(@PathVariable("incomeId") Long incomeId) {
        try {
            def deleted = transactionHistoryService.deleteIncome(incomeId)
            if (deleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body([
                    status : HttpStatus.NO_CONTENT.value(),
                    message: "Income record deleted successfully",
                    data   : null
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "Income record not found",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "Error deleting income record: ${e.message}",
                data   : null
            ])
        }
    }

    /**
     * Delete Expense
     */
    @DeleteMapping("/expense/{expenseId}")
    ResponseEntity<?> deleteExpense(@PathVariable("expenseId") Long expenseId) {
        try {
            def deleted = transactionHistoryService.deleteExpense(expenseId)
            if (deleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body([
                    status : HttpStatus.NO_CONTENT.value(),
                    message: "Expense record deleted successfully",
                    data   : null
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "Expense record not found",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "Error deleting expense record: ${e.message}",
                data   : null
            ])
        }
    }
}
