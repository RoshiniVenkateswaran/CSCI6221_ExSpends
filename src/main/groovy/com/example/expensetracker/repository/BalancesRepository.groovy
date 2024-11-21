package com.example.expensetracker.repository

import com.example.expensetracker.entity.Balances
import org.springframework.data.jpa.repository.JpaRepository

interface BalancesRepository extends JpaRepository<Balances, Long> {}
