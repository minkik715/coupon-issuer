package com.example.consumer.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Coupon(
    @Id
    val id: String = UUID.randomUUID().toString(),

    var userId: Long? = null,

    ) {
    constructor(userId: Long) : this(
        UUID.randomUUID().toString(),
        userId
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coupon

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

