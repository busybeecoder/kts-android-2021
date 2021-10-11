package com.bignerdranch.android.ktsapplication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FullActivity(
    val name: String,
    val distance: Float,
    @Json(name = "moving_time")
    val time: Int,
    @Json(name = "average_speed")
    val avgSpeed: Float
)
