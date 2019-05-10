package com.example.octoevents.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class IssuePayload(val number: Int,
                        @JsonProperty("created_at") val createdAt: LocalDateTime,
                        @JsonProperty("updated_at") val updatedAt: LocalDateTime)