package com.example.octoevents.service.impl

import com.example.octoevents.model.dto.EventPayload
import com.example.octoevents.model.entity.IssueEvent
import com.example.octoevents.model.entity.Issue
import com.example.octoevents.repository.IssueRepository
import com.example.octoevents.service.IssueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IssueServiceImpl : IssueService {

    @Autowired
    lateinit var issueRepository : IssueRepository

    override fun registerEvent(eventPayload: EventPayload): IssueEvent {
        var issue: Issue? = issueRepository.findByNumber(eventPayload.issue.number)

        if (issue == null){
            issue = Issue()
            issue.number = eventPayload.issue.number
            issue.createdAt = eventPayload.issue.createdAt

            issueRepository.save(issue)
        }

        val newIssueEvent = IssueEvent()
        newIssueEvent.issue = issue
        newIssueEvent.createdAt = eventPayload.issue.updatedAt
        newIssueEvent.action = eventPayload.action

        return newIssueEvent
    }

    override fun getIssueEvents(issueNumber: Int): List<IssueEvent> {
        val issueEventUm = IssueEvent()
        issueEventUm.action = "Created"


        val issueEventDois = IssueEvent()
        issueEventDois.action = "Closed"


        return listOf(issueEventUm, issueEventDois)

    }
}