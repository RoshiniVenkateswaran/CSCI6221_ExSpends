package com.example.expensetracker.repository

import com.example.expensetracker.entity.Source
import org.springframework.data.jpa.repository.JpaRepository

interface SourceRepository extends JpaRepository<Source, Long> {}
