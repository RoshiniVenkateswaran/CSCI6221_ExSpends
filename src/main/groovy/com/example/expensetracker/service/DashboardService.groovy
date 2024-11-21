package com.example.expensetracker.service

import com.example.expensetracker.repository.IncomeRepository
import com.example.expensetracker.repository.ExpensesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DashboardService {

    @Autowired
    IncomeRepository incomeRepository

    @Autowired
    ExpensesRepository expensesRepository

    def getDashboardData(Long accountId) {
        def incomeData = incomeRepository.findByAccountId(accountId)
        def expenseData = expensesRepository.findByAccountId(accountId)

        if (!incomeData || !expenseData) {
            return [
                message: "No data available for the given accountId",
                income: [],
                expenses: []
            ]
        }

        return [
            totalIncome: incomeData.sum { it.amount },
            totalExpenses: expenseData.sum { it.amount },
            incomeDetails: incomeData,
            expenseDetails: expenseData,
            message: "Dashboard data retrieved successfully"
        ]
    }
}
