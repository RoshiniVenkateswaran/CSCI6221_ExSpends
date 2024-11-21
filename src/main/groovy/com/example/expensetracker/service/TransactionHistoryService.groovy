package com.example.expensetracker.service

import com.example.expensetracker.repository.IncomeRepository
import com.example.expensetracker.repository.ExpensesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class TransactionHistoryService {

    @Autowired
    IncomeRepository incomeRepository

    @Autowired
    ExpensesRepository expensesRepository

    def getAllTransactions() {
        try {
            def incomeHistory = incomeRepository.findAll()
            def expenseHistory = expensesRepository.findAll()

            return [
                incomeHistory: incomeHistory,
                expenseHistory: expenseHistory
            ]
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving transaction history: ${e.message}")
        }
    }

    def updateIncome(Long incomeId, Map<String, Object> incomeDetails) {
        try {
            def income = incomeRepository.findById(incomeId).orElse(null)
            if (income) {
                income.amount = incomeDetails.get("amount")
                income.notes = incomeDetails.get("notes")

                // Safely parse and set the entryDate
                String entryDateStr = incomeDetails.get("entryDate")
                if (entryDateStr) {
                    income.entryDate = LocalDateTime.parse(entryDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                }

                income.sourceId = incomeDetails.get("sourceId")
                incomeRepository.save(income)
                return [message: "Income record updated successfully."]
            }
            return [message: "Income record not found."]
        } catch (Exception e) {
            throw new RuntimeException("Error updating income record: ${e.message}")
        }
    }

    def updateExpense(Long expenseId, Map<String, Object> expenseDetails) {
        try {
            def expense = expensesRepository.findById(expenseId).orElse(null)
            if (expense) {
                expense.amount = expenseDetails.get("amount")
                expense.notes = expenseDetails.get("notes")

                // Safely parse and set the entryDate
                String entryDateStr = expenseDetails.get("entryDate")
                if (entryDateStr) {
                    expense.entryDate = LocalDateTime.parse(entryDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                }

                expense.categoryId = expenseDetails.get("categoryId")
                expensesRepository.save(expense)
                return [message: "Expense record updated successfully."]
            }
            return [message: "Expense record not found."]
        } catch (Exception e) {
            throw new RuntimeException("Error updating expense record: ${e.message}")
        }
    }

    def deleteIncome(Long incomeId) {
        try {
            if (incomeRepository.existsById(incomeId)) {
                incomeRepository.deleteById(incomeId)
                return [message: "Income record deleted successfully."]
            }
            return [message: "Income record not found."]
        } catch (Exception e) {
            throw new RuntimeException("Error deleting income record: ${e.message}")
        }
    }

    def deleteExpense(Long expenseId) {
        try {
            if (expensesRepository.existsById(expenseId)) {
                expensesRepository.deleteById(expenseId)
                return [message: "Expense record deleted successfully."]
            }
            return [message: "Expense record not found."]
        } catch (Exception e) {
            throw new RuntimeException("Error deleting expense record: ${e.message}")
        }
    }
}
