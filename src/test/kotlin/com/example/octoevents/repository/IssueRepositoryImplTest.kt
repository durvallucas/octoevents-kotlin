package com.example.octoevents.repository

import com.example.octoevents.database.Issues
import com.example.octoevents.model.Issue
import com.example.octoevents.repository.impl.IssueRepositoryImpl
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class IssueRepositoryImplTest{

    private val repository = IssueRepositoryImpl()

    @Before
    fun setUp() {
        Database.connect("jdbc:h2:mem:test;INIT=CREATE SCHEMA IF NOT EXISTS octo", driver = "org.h2.Driver")
    }

    @Test
    fun whenANumberIsInformed_andNotExistAnIssueForThatNumber_thenNullIsReturned() {
        val issue1 = Issue()
        issue1.number = 1000
        issue1.createdAt  = DateTime.now()

        transaction {
            SchemaUtils.create(Issues)
            repository.save(issue1)

            val issue = repository.findByNumber(2000)
            assertNull(issue)
        }
    }

    @Test
    fun whenANumberIsInformed_andExistAnIssueForThatNumber_thenCorrespondingIssueIsReturned() {
        val issue1 = Issue()
        issue1.number = 1000
        issue1.createdAt  = DateTime.now()

        val issue2 = Issue()
        issue2.number = 2000
        issue2.createdAt  = DateTime.now()

        transaction {
            SchemaUtils.create(Issues)
            repository.save(issue1)
            repository.save(issue2)

            val issue = repository.findByNumber(1000)
            assertEquals(issue1.number, issue?.number)
        }
    }
}