package com.example.octoevents.model

import org.joda.time.DateTime

class Issue {
    var id: Int? = null
    var number: Int = 0
    var createdAt: DateTime = DateTime.now()
}