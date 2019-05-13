package com.example.octoevents.repository.impl

import com.example.octoevents.database.Issues
import com.example.octoevents.model.Issue
import com.example.octoevents.repository.IssueRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class IssueRepositoryImpl: IssueRepository {

    override fun save(issue: Issue): Issue {
        val nextId = getNextId()

        Issues.insert {
            it[id] = nextId
            it[number] = issue.number
            it[createdAt] = issue.createdAt
        }

        issue.id = nextId
        return issue
    }

    override fun findByNumber(number: Int): Issue? {
        val resultRow = Issues.select { Issues.number eq number }.firstOrNull() ?: return null
        return fromRow(resultRow)
    }

    private fun fromRow(r: ResultRow): Issue {
        val issue = Issue()
        issue.id = r[Issues.id]
        issue.number = r[Issues.number]
        issue.createdAt = r[Issues.createdAt]

        return issue
    }

    private fun getNextId(): Int {
        val resultRow = Issues
                .selectAll()
                .orderBy(Issues.id, false)
                .firstOrNull()  ?: return 1

        return resultRow[Issues.id] + 1

    }
}
