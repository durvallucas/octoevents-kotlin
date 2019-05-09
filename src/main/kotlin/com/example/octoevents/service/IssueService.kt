package com.example.octoevents.service

interface IssueService {
    fun registerEvent()

    fun getIssueEvents(issueNumber: Int)
}