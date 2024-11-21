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

    def getDashboardData() {
        def incomeData = incomeRepository.findAll()
        def expenseData = expensesRepository.findAll()

        if (!incomeData || !expenseData) {
            return [
                message: "No data available",
                income: [],
                expenses: []
            ]
        }

        return [
            totalIncome: incomeData.sum { it.amount },
            totalExpenses: expenseData.sum { it.amount },
            message: "Dashboard data retrieved successfully"
        ]
    }
}
