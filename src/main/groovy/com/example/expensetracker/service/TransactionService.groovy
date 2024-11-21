package com.example.expensetracker.service

import com.example.expensetracker.entity.Income
import com.example.expensetracker.entity.Expenses
import com.example.expensetracker.repository.IncomeRepository
import com.example.expensetracker.repository.ExpensesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class TransactionService {

    @Autowired
    IncomeRepository incomeRepository

    @Autowired
    ExpensesRepository expensesRepository

    def addIncome(Map<String, Object> incomeDetails) {
        try {
            def income = new Income(
                accountId: incomeDetails.accountId as Long,
                sourceId: incomeDetails.sourceId as Long,
                amount: incomeDetails.amount as Double,
                entryDate: incomeDetails.entryDate as String,
                notes: incomeDetails.notes as String
            )
            def savedIncome = incomeRepository.save(income)
            return [message: "Income added successfully", incomeId: savedIncome.incomeId]
        } catch (Exception e) {
            throw new RuntimeException("Error adding income: ${e.message}")
        }
    }

    def addExpense(Map<String, Object> expenseDetails) {
        try {
            def expense = new Expenses(
                accountId: expenseDetails.accountId as Long,
                categoryId: expenseDetails.categoryId as Long,
                amount: expenseDetails.amount as Double,
                entryDate: expenseDetails.entryDate as String,
                notes: expenseDetails.notes as String
            )
            def savedExpense = expensesRepository.save(expense)
            return [message: "Expense added successfully", expenseId: savedExpense.expenseId]
        } catch (Exception e) {
            throw new RuntimeException("Error adding expense: ${e.message}")
        }
    }
}
