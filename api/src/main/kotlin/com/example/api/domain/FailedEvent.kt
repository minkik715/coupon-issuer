package com.example.api.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class FailedEvent(
    @Id
    val id: String = UUID.randomUUID().toString(),
    var userId: Long = 0L
) {
    constructor(userId: Long) : this(UUID.randomUUID().toString(), userId)
}