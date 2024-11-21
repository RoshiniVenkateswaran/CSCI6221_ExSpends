package com.example.expensetracker.controller

import com.example.expensetracker.service.StatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class StatisticsController {

    @Autowired
    StatisticsService statisticsService

    @GetMapping("/statistics")
    ResponseEntity<?> getStatistics(@RequestParam("accountId") Long accountId) {
        try {
            def stats = statisticsService.calculateStatistics(accountId)
            return ResponseEntity.ok(stats)
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(500).body([message: "An error occurred: ${e.message}"])
        }
    }
}
