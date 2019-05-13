package com.example.octoevents.model

import org.joda.time.DateTime

class IssueEvent {
    var id: Int? = null
    var action: String = ""
    var createdAt: DateTime = DateTime.now()
    var issue: Issue = Issue()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IssueEvent

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id ?: 0
    }


}