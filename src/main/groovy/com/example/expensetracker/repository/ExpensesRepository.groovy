package com.example.expensetracker.repository

import com.example.expensetracker.entity.Expenses
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    List<Expenses> findByAccountId(Long accountId)
}
