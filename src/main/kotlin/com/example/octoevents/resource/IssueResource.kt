package com.example.octoevents.resource

import com.example.octoevents.service.IssueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/issues")
class IssueResource {

    @Autowired
    lateinit var issueService : IssueService

    @PostMapping
    fun register() {
        issueService.registerEvent()
    }

    @GetMapping("/{number}/events")
    @ResponseStatus(HttpStatus.OK)
    fun searchEvents(@PathVariable number: Int) {
        issueService.getIssueEvents(number)
    }

}