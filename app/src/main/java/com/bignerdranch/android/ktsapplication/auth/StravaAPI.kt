package com.bignerdranch.android.ktsapplication.auth

import com.bignerdranch.android.ktsapplication.Activities
import retrofit2.http.GET
import retrofit2.http.Query

interface StravaAPI {
    @GET("athlete/activities")
    suspend fun getActivities(
        @Query("before") before: Int,
        @Query("after") after: Int
    ): List<Activities>
}
