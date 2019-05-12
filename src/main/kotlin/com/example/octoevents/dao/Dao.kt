package com.example.octoevents.dao

import org.jetbrains.exposed.sql.Table

object Issues : Table("octo.issue"){
    val id = integer("id").autoIncrement("octo.sq_issue").primaryKey()
    val number = integer("number")
    val createdAt = date("created_at")
}

object IssueEvents : Table("octo.issue_event"){
    val id = integer("id").autoIncrement("sq_issue_event").primaryKey()
    val action = varchar("action", 15)
    val createdAt = date("created_at")
    val issue = (integer("issue_id") references Issues.id).nullable()
}