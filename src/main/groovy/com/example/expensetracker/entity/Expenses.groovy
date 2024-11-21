package com.example.expensetracker.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "expenses")
class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    Integer expenseId

    @Column(name = "account_id", nullable = false)
    Integer accountId

    @Column(name = "category_id", nullable = false)
    Integer categoryId

    @Column(name = "amount", nullable = false)
    Double amount

    @Column(name = "entry_date", nullable = false)
    LocalDateTime entryDate

    @Column(name = "notes")
    String notes
}
