package com.example.expensetracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "Category")
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer categoryId

    @Column(nullable = false, unique = true, length = 50)
    String name

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean isPredefined = false

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account
}
