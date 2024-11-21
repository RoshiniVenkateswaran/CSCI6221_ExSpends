package com.example.expensetracker.controller

import com.example.expensetracker.entity.Expenses
import com.example.expensetracker.service.ExpenseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/expenses")
class ExpenseController {

    @Autowired
    ExpenseService expenseService

    @GetMapping
    List<Expenses> getExpenses(@RequestParam Long accountId) {
        return expenseService.getExpenses(accountId)
    }

    @PostMapping
    Expenses addExpense(@RequestBody Expenses expense) {
        return expenseService.addExpense(expense)
    }
}
