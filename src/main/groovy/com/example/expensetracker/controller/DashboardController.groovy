package com.example.expensetracker.controller

import com.example.expensetracker.service.DashboardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DashboardController {

    @Autowired
    DashboardService dashboardService

    @GetMapping("/dashboard")
    ResponseEntity<?> getDashboard(@RequestParam(value = "accountId") Long accountId) {
        try {
            def dashboardData = dashboardService.getDashboardData(accountId)
            if (dashboardData) {
                return ResponseEntity.ok([
                    status : HttpStatus.OK.value(),
                    message: "Dashboard data retrieved successfully",
                    data   : dashboardData
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "No dashboard data found for accountId ${accountId}"
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred while retrieving dashboard data: ${e.message}"
            ])
        }
    }
}
