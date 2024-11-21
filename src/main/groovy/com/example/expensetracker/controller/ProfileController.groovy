package com.example.expensetracker.controller

import com.example.expensetracker.service.ProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ProfileController {

    @Autowired
    ProfileService profileService

    @GetMapping("/profile")
    ResponseEntity<?> getProfile(@RequestParam("accountId") Integer accountId) {
        try {
            def profile = profileService.getProfile(accountId)
            return profile ? ResponseEntity.ok(profile) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body([message: "No profile found for accountId ${accountId}"])
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "An error occurred: ${e.message}"])
        }
    }

    @PutMapping("/profile")
    ResponseEntity<?> updateProfile(@RequestBody Map<String, Object> profileDetails) {
        try {
            def updatedProfile = profileService.updateProfile(profileDetails)
            return updatedProfile ? ResponseEntity.ok([message: "Profile updated successfully", updatedProfile: updatedProfile]) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body([message: "Profile update failed"])
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body([message: "An error occurred: ${e.message}"])
        }
    }
}
