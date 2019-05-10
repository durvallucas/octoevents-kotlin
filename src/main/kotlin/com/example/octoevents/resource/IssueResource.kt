package com.example.octoevents.resource

import com.example.octoevents.model.dto.EventPayload
import com.example.octoevents.model.entity.IssueEvent
import com.example.octoevents.service.IssueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/issues")
class IssueResource {

    @Autowired
    lateinit var issueService : IssueService

    @PostMapping
    fun register(@RequestBody eventPayload: EventPayload): ResponseEntity<IssueEvent> {
        val issueEvent = issueService.registerEvent(eventPayload)
        return ResponseEntity.status(HttpStatus.CREATED).body(issueEvent)
    }

    @GetMapping("/{number}/events")
    @ResponseStatus(HttpStatus.OK)
    fun searchEvents(@PathVariable number: Int): List<IssueEvent> {
        return issueService.getIssueEvents(number)
    }

}