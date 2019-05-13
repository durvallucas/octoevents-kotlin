package com.example.octoevents.service.impl

import com.example.octoevents.model.payload.EventPayload
import com.example.octoevents.model.payload.IssuePayload
import com.example.octoevents.model.Issue
import com.example.octoevents.model.IssueEvent
import com.example.octoevents.repository.IssueEventRepository
import com.example.octoevents.repository.IssueRepository
import com.example.octoevents.service.IssueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueServiceImpl : IssueService {

    @Autowired
    lateinit var issueRepository : IssueRepository

    @Autowired
    lateinit var issueEventRepository : IssueEventRepository

    @Transactional
    override fun registerEvent(eventPayload: EventPayload): IssueEvent {
        var issue = issueRepository.findByNumber(eventPayload.issue.number) ?: registerNewIssue(eventPayload.issue)

        val newIssueEvent = IssueEvent()
        newIssueEvent.issue = issue
        newIssueEvent.createdAt = eventPayload.issue.updatedAt
        newIssueEvent.action = eventPayload.action
        issueEventRepository.save(newIssueEvent)

        return newIssueEvent
    }

    @Transactional
    override fun getIssueEvents(issueNumber: Int): List<IssueEvent> {
        return issueEventRepository.findByIssueNumber(issueNumber)
    }

    private fun registerNewIssue(issuePayload: IssuePayload): Issue {
        val newIssue = Issue()
        newIssue.number = issuePayload.number
        newIssue.createdAt = issuePayload.createdAt

        issueRepository.save(newIssue)

        return newIssue
    }
}