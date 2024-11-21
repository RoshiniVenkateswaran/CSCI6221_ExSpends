package com.example.expensetracker.repository

import com.example.expensetracker.entity.Income
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByAccountId(Long accountId)
}
