package com.bignerdranch.android.ktsapplication

import java.util.*

data class PostText(
    val userName: String,
    val uuid: UUID,
    val userAvatar: Int,
    val tweet: String
)
