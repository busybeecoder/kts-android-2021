package com.bignerdranch.android.ktsapplication.api

import com.bignerdranch.android.ktsapplication.api.Activities
import com.bignerdranch.android.ktsapplication.auth.Networking

class ActivitiesRepository {
    suspend fun getActivities(): List<Activities> {
        return Networking.stravaApi.getLoggedInAthleteActivities()
    }
}