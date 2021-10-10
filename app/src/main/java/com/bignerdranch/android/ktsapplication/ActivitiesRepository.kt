package com.bignerdranch.android.ktsapplication

import com.bignerdranch.android.ktsapplication.auth.Networking

class ActivitiesRepository {
    suspend fun getAthleteActivities(
        before: Int,
        after: Int
    ): List<Activities> {
        return Networking.stravaApi.getActivities(before, after)
    }
}