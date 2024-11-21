package com.example.expensetracker.service

import com.example.expensetracker.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProfileService {

    @Autowired
    AccountRepository accountRepository

    def getProfile(Integer accountId) {
        accountRepository.findById(accountId).orElse(null)
    }

    def updateProfile(Map<String, Object> profileDetails) {
        def accountId = profileDetails.accountId as Long
        def account = accountRepository.findById(accountId).orElse(null)

        if (!account) {
            return null
        }

        account.firstName = profileDetails.firstName ?: account.firstName
        account.lastName = profileDetails.lastName ?: account.lastName
        account.currency = profileDetails.currency ?: account.currency
        accountRepository.save(account)
    }
}
