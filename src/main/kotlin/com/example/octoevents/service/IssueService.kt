package com.example.octoevents.service

import com.example.octoevents.model.dto.EventPayload
import com.example.octoevents.model.entity.IssueEvent

interface IssueService {
    fun registerEvent(eventPayload: EventPayload): IssueEvent

    fun getIssueEvents(issueNumber: Int): List<IssueEvent>
}