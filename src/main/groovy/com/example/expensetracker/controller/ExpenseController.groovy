package com.example.expensetracker.controller

import com.example.expensetracker.entity.Expenses
import com.example.expensetracker.service.ExpenseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/expenses")
class ExpenseController {

    @Autowired
    ExpenseService expenseService

    @GetMapping
    ResponseEntity<?> getExpenses(@RequestParam Long accountId) {
        try {
            def expenses = expenseService.getExpenses(accountId)
            if (!expenses.isEmpty()) {
                return ResponseEntity.ok([
                    status : HttpStatus.OK.value(),
                    message: "Expenses retrieved successfully",
                    data   : expenses
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "No expenses found for accountId ${accountId}"
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred while retrieving expenses: ${e.message}"
            ])
        }
    }

    @PostMapping
    ResponseEntity<?> addExpense(@RequestBody Expenses expense) {
        try {
            def addedExpense = expenseService.addExpense(expense)
            return ResponseEntity.status(HttpStatus.CREATED).body([
                status : HttpStatus.CREATED.value(),
                message: "Expense added successfully",
                data   : addedExpense
            ])
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred while adding the expense: ${e.message}"
            ])
        }
    }
}
