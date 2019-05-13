package com.example.octoevents.repository

import com.example.octoevents.database.IssueEvents
import com.example.octoevents.database.Issues
import com.example.octoevents.model.Issue
import com.example.octoevents.model.IssueEvent
import com.example.octoevents.repository.impl.IssueEventRepositoryImpl
import com.example.octoevents.repository.impl.IssueRepositoryImpl
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class IssueEventRepositoryImplTest{
    private val issueEventRepository = IssueEventRepositoryImpl()
    private val issueRepository = IssueRepositoryImpl()

    @Before
    fun setUp() {
        Database.connect("jdbc:h2:mem:test;INIT=CREATE SCHEMA IF NOT EXISTS octo", driver = "org.h2.Driver")
    }

    @Test
    fun whenIssueNumberIsInformed_thenEventsForThatIssueIsReturned(){
        val issue1 = Issue()
        issue1.number = 1000
        issue1.createdAt  = DateTime.now()

        val issue2 = Issue()
        issue2.number = 2000
        issue2.createdAt  = DateTime.now()

        val issueEvent1 = IssueEvent()
        issueEvent1.action = "Created"
        issueEvent1.createdAt = DateTime()
        issueEvent1.issue = issue1

        val issueEvent2 = IssueEvent()
        issueEvent2.action = "Closed"
        issueEvent2.createdAt = DateTime()
        issueEvent2.issue = issue1

        val issueEvent3 = IssueEvent()
        issueEvent3.action = "Created"
        issueEvent3.createdAt = DateTime()
        issueEvent3.issue = issue2

        transaction {
            SchemaUtils.create(Issues, IssueEvents)
            issueRepository.save(issue1)
            issueRepository.save(issue2)

            issueEventRepository.save(issueEvent1)
            issueEventRepository.save(issueEvent2)
            issueEventRepository.save(issueEvent3)

            val events = issueEventRepository.findByIssueNumber(1000)

            assertEquals(2, events.size)
            assertTrue(events.contains(issueEvent1))
            assertTrue(events.contains(issueEvent2))
            assertFalse(events.contains(issueEvent3))
        }


    }



}