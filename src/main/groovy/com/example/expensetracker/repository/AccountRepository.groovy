package com.example.expensetracker.repository

import com.example.expensetracker.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username)
    Optional<Account> findByEmailAddress(String emailAddress)
}
