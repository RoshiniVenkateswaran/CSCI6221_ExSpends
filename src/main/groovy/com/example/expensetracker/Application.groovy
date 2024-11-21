package com.example.expensetracker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.expensetracker.repository")
class Application {
    static void main(String[] args) {
        SpringApplication.run(Application, args)
    }
}
