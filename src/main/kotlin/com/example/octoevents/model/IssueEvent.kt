package com.example.octoevents.model

import org.joda.time.DateTime

class IssueEvent {
    var id: Int? = null
    var action: String = ""
    var createdAt: DateTime = DateTime.now()
    var issue: Issue = Issue()
}