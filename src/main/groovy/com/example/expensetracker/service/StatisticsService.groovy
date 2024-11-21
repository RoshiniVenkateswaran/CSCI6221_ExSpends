package com.example.expensetracker.service

import com.example.expensetracker.repository.IncomeRepository
import com.example.expensetracker.repository.ExpensesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatisticsService {

    @Autowired
    IncomeRepository incomeRepository

    @Autowired
    ExpensesRepository expensesRepository

    def calculateStatistics(Long accountId) {
        def incomes = incomeRepository.findByAccountId(accountId)
        def expenses = expensesRepository.findByAccountId(accountId)

        if (!incomes && !expenses) {
            return [message: "No data available for account ID ${accountId}"]
        }

        def totalIncome = incomes.sum { it.amount }
        def totalExpenses = expenses.sum { it.amount }

        return [
            totalIncome    : totalIncome ?: 0,
            totalExpenses  : totalExpenses ?: 0,
            balance        : (totalIncome ?: 0) - (totalExpenses ?: 0),
            message        : "Statistics calculated successfully"
        ]
    }
}
