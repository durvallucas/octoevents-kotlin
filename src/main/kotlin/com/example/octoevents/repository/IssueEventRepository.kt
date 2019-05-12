package com.example.octoevents.repository

import com.example.octoevents.model.IssueEvent

interface IssueEventRepository {
    fun save(issueEvent: IssueEvent): IssueEvent

    fun findByIssueNumber(number: Int): List<IssueEvent>
}