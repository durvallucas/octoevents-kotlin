package com.example.octoevents.service

import com.example.octoevents.model.Issue
import com.example.octoevents.model.IssueEvent
import com.example.octoevents.model.payload.EventPayload
import com.example.octoevents.model.payload.IssuePayload
import com.example.octoevents.repository.IssueEventRepository
import com.example.octoevents.repository.IssueRepository
import com.example.octoevents.service.impl.IssueServiceImpl
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class IssueServiceImplTest {

    @TestConfiguration
    internal class IssueServiceImplContextConfiguration {

        @Bean
        fun issueService(): IssueService {
            return IssueServiceImpl()
        }
    }

    @Autowired
    lateinit var issueService: IssueServiceImpl

    @MockBean
    lateinit var issueRepository: IssueRepository

    @MockBean
    lateinit var issueEventRepository: IssueEventRepository

    lateinit var issuePayload: IssuePayload
    lateinit var eventPayload: EventPayload

    @Before
    fun setUp() {
        issuePayload = IssuePayload(500, DateTime().withDate(2019, 5, 11), DateTime().withDate(2019, 5, 11))
        eventPayload =  EventPayload("Created", issuePayload)
    }

    @Test
    fun whenEventIsRegisteredForNonExistentIssue_thenAnIssueIsCreated(){
        whenever(issueRepository.findByNumber(issuePayload.number)).thenReturn(null)

        val issueEvent = issueService.registerEvent(eventPayload)

        verify(issueRepository).save(issueEvent.issue)
        assertEquals(issuePayload.createdAt, issueEvent.issue.createdAt)
        assertEquals(issuePayload.number, issueEvent.issue.number)
    }

    @Test
    fun whenEventIsRegisteredForExistentIssue_thenEventIsCreatedForIssue(){
        val issue = Issue()
        whenever(issueRepository.findByNumber(issuePayload.number)).thenReturn(issue)

        val issueEvent = issueService.registerEvent(eventPayload)

        verify(issueEventRepository).save(issueEvent)
        assertEquals(issue, issueEvent.issue)
        assertEquals(eventPayload.action, issueEvent.action)
        assertEquals(issuePayload.updatedAt, issueEvent.createdAt)
    }

    @Test
    fun whenAnIssueNumberIsInformed_thenEventsAreReturnedForThatIssue(){
        val issueEvent1 = IssueEvent()
        val issueEvent2 = IssueEvent()
        val issueNumber = 1000

        whenever(issueEventRepository.findByIssueNumber(issueNumber)).thenReturn(listOf(issueEvent1, issueEvent2))

        val events = issueService.getIssueEvents(issueNumber)

        assertEquals(2, events.size)
        assertTrue(events.contains(issueEvent1))
        assertTrue(events.contains(issueEvent2))
    }

}