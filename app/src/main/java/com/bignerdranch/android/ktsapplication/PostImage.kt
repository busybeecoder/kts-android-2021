package com.bignerdranch.android.ktsapplication

import java.util.*

data class PostImage(
    val userName: String,
    val uuid: UUID,
    val userAvatar: Int,
    val picture: Int,
    var likesCount: Int
)
