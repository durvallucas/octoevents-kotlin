package com.example.octoevents.model

import org.joda.time.DateTime

class Issue {
    var id: Int? = null
    var number: Int = 0
    var createdAt: DateTime = DateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Issue

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id ?: 0
    }


}