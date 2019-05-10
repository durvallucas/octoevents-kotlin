package com.example.octoevents.model.entity

import java.time.LocalDateTime

class IssueEvent {
    var action: String = ""
    var createdAt = LocalDateTime.now()
    var issue: Issue = Issue()

}


