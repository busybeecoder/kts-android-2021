package com.bignerdranch.android.ktsapplication.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Activities(
    val id: Long,
    val name: String,
    val distance: Float,
    @Json(name = "kudos_count")
    val likes: Int
)
