package com.example.octoevents.service.impl

import com.example.octoevents.model.dto.EventPayload
import com.example.octoevents.model.entity.IssueEvent
import com.example.octoevents.repository.IssueRepository
import com.example.octoevents.service.IssueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IssueServiceImpl : IssueService {

    @Autowired
    lateinit var issueRepository : IssueRepository

    override fun registerEvent(eventPayload: EventPayload): IssueEvent {
        val newIssueEvent = IssueEvent()
        newIssueEvent.action = eventPayload.action
        newIssueEvent.issueNumber = eventPayload.issue.number

        return newIssueEvent
    }

    override fun getIssueEvents(issueNumber: Int): List<IssueEvent> {
        val issueEventUm = IssueEvent()
        issueEventUm.action = "Created"
        issueEventUm.issueNumber = 1000

        val issueEventDois = IssueEvent()
        issueEventDois.action = "Closed"
        issueEventDois.issueNumber = 1000

        return listOf(issueEventUm, issueEventDois)

    }
}