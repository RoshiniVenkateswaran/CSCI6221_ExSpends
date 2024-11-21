package com.example.expensetracker.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Account")
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer accountId

    @Column(nullable = false, unique = true, length = 50)
    String username

    @Column(nullable = false, length = 200)
    String password

    @Column(nullable = false, length = 25)
    String firstName

    @Column(nullable = false, length = 25)
    String lastName

    @Column(nullable = false, unique = true, length = 50)
    String emailAddress

    @Column(length = 20)
    String currency

    @Column(nullable = false)
    LocalDateTime createdAt = LocalDateTime.now()
}
