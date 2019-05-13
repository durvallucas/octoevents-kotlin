package com.example.octoevents.service

import com.example.octoevents.model.payload.EventPayload
import com.example.octoevents.model.IssueEvent


interface IssueService {
    fun registerEvent(eventPayload: EventPayload): IssueEvent

    fun getIssueEvents(issueNumber: Int): List<IssueEvent>
}