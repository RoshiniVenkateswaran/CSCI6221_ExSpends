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

    /**
     * Get Profile by Account ID
     */
    @GetMapping("/profile")
    ResponseEntity<?> getProfile(@RequestParam("accountId") Integer accountId) {
        try {
            def profile = profileService.getProfile(accountId)
            if (profile) {
                return ResponseEntity.ok([
                    status : HttpStatus.OK.value(),
                    message: "Profile retrieved successfully",
                    data   : profile
                ])
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body([
                    status : HttpStatus.NOT_FOUND.value(),
                    message: "No profile found for accountId ${accountId}",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred while retrieving the profile: ${e.message}",
                data   : null
            ])
        }
    }

    /**
     * Update Profile
     */
    @PutMapping("/profile")
    ResponseEntity<?> updateProfile(@RequestBody Map<String, Object> profileDetails) {
        try {
            def updatedProfile = profileService.updateProfile(profileDetails)
            if (updatedProfile) {
                return ResponseEntity.ok([
                    status : HttpStatus.OK.value(),
                    message: "Profile updated successfully",
                    data   : updatedProfile
                ])
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body([
                    status : HttpStatus.BAD_REQUEST.value(),
                    message: "Failed to update profile",
                    data   : null
                ])
            }
        } catch (Exception e) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body([
                status : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message: "An error occurred while updating the profile: ${e.message}",
                data   : null
            ])
        }
    }
}
