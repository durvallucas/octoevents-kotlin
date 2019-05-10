package com.example.octoevents.repository.impl

import com.example.octoevents.model.entity.Issue
import com.example.octoevents.repository.IssueRepository
import org.springframework.stereotype.Repository

@Repository
class IssueRepositoryImpl: IssueRepository {

    override fun save(issue: Issue): Issue {
        return issue
    }

    override fun findByNumber(number: Int): Issue {
        return Issue()
    }
}
