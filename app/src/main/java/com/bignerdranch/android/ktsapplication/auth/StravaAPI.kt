package com.bignerdranch.android.ktsapplication.auth

import com.bignerdranch.android.ktsapplication.api.Activities
import com.bignerdranch.android.ktsapplication.api.FullActivity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StravaAPI {
    @GET("athlete/activities")
    suspend fun getLoggedInAthleteActivities(): List<Activities>

    @GET("activities/{id}")
    suspend fun getActivityById(
        @Path("id") id: Long,
        @Query("include_all_efforts") include_all_efforts: Boolean
    ): FullActivity
}
