package com.example.expensetracker.controller

import com.example.expensetracker.service.StatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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
            if (stats) {
                return ResponseEntity.ok([
                    status : HttpStatus.OK.value(),
                    message: "Statistics retrieved successfully",
                    data   : stats
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "No statistics found for accountId ${accountId}",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred while retrieving statistics: ${e.message}",
                data   : null
            ])
        }
    }
}
