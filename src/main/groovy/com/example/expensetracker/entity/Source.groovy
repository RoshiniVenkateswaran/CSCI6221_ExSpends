package com.example.expensetracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "Source")
class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer sourceId

    @Column(nullable = false, length = 50)
    String name

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean isPredefined = false

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account
}
