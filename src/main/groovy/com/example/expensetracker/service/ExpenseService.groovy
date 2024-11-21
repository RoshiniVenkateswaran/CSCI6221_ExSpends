package com.example.expensetracker.service

import com.example.expensetracker.entity.Expenses
import com.example.expensetracker.repository.ExpensesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExpenseService {

    @Autowired
    ExpensesRepository expensesRepository

    List<Expenses> getExpenses(Long accountId) {
        return expensesRepository.findByAccountId(accountId)
    }

    Expenses addExpense(Expenses expense) {
        return expensesRepository.save(expense)
    }
}
