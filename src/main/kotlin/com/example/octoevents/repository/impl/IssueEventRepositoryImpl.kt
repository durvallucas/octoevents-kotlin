package com.example.octoevents.repository.impl

import com.example.octoevents.database.IssueEvents
import com.example.octoevents.database.Issues
import com.example.octoevents.model.IssueEvent
import com.example.octoevents.repository.IssueEventRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class IssueEventRepositoryImpl: IssueEventRepository {

    override fun save(issueEvent: IssueEvent): IssueEvent {
        val nextId = getNextId()
        IssueEvents.insert {
            it[id] = nextId
            it[action] = issueEvent.action
            it[createdAt] = issueEvent.createdAt
            it[issue] = issueEvent.issue.id
        }

        issueEvent.id = nextId
        return issueEvent
    }

    override fun findByIssueNumber(number: Int): List<IssueEvent> {
        return (IssueEvents innerJoin Issues).select{ Issues.number eq number}.map { fromRow(it) }
    }

    private fun fromRow(r: ResultRow): IssueEvent {
        val issueEvent = IssueEvent()

        issueEvent.id = r[IssueEvents.id]
        issueEvent.action = r[IssueEvents.action]
        issueEvent.createdAt = r[IssueEvents.createdAt]
        issueEvent.issue.id = r[Issues.id]
        issueEvent.issue.number = r[Issues.number]
        issueEvent.issue.createdAt = r[Issues.createdAt]

        return issueEvent
    }

    private fun getNextId(): Int {
        val resultRow = IssueEvents
                .selectAll()
                .orderBy(IssueEvents.id, false)
                .firstOrNull()  ?: return 1

        return resultRow[IssueEvents.id] + 1

    }

}
