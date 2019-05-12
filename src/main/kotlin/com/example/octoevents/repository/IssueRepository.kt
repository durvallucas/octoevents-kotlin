package com.example.octoevents.repository

import com.example.octoevents.model.Issue

interface IssueRepository {

    fun save(issue: Issue): Issue

    fun findByNumber(number: Int): Issue?
}