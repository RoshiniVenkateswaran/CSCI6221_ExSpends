package com.example.expensetracker.controller

import com.example.expensetracker.service.DashboardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DashboardController {

    @Autowired
    DashboardService dashboardService

    @GetMapping("/dashboard")
    ResponseEntity<?> getDashboard() {
        try {
            def dashboardData = dashboardService.getDashboardData()
            return ResponseEntity.ok(dashboardData)
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "An error occurred: ${e.message}"])
        }
    }
}