package com.example.octoevents.model.payload

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

data class IssuePayload(val number: Int,
                        @JsonProperty("created_at") val createdAt: DateTime,
                        @JsonProperty("updated_at") val updatedAt: DateTime)