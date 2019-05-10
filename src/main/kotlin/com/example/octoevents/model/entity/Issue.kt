package com.example.octoevents.model.entity

import java.time.LocalDateTime

class Issue {
    var number: Int = 0
    var createdAt: LocalDateTime =  LocalDateTime.now()
}