package com.example.octoevents.database

import org.jetbrains.exposed.sql.Table

object Issues : Table("octo.issue"){
    val id = integer("id").autoIncrement().primaryKey()
    val number = integer("number")
    val createdAt = datetime("created_at")
}

object IssueEvents : Table("octo.issue_event"){
    val id = integer("id").autoIncrement().primaryKey()
    val action = varchar("action", 15)
    val createdAt = datetime("created_at")
    val issue = (integer("issue_id") references Issues.id).nullable()
}