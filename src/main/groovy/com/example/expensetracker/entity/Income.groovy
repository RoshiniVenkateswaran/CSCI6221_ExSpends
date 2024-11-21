package com.example.expensetracker.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "income")
class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    Integer incomeId

    @Column(name = "account_id", nullable = false)
    Integer accountId

    @Column(name = "source_id", nullable = false)
    Integer sourceId

    @Column(name = "amount", nullable = false)
    Double amount

    @Column(name = "entry_date", nullable = false)
    LocalDateTime entryDate

    @Column(name = "notes")
    String notes
}
