package com.example.expensetracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "Balances")
class Balances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer balanceId

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    Account account

    @Column(nullable = false, length = 50)
    String name

    @Enumerated(EnumType.STRING)  // Map ENUM to String
    @Column(name = "type", nullable = false, length = 50)
    BalanceType type

    @Column(nullable = false, columnDefinition = "REAL DEFAULT 0")
    Float amount = 0

    @Column(columnDefinition = "TEXT")
    String notes

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean isPredefined = false
}

// Enum for type
enum BalanceType {
    ASSET, LIABILITY
}
