package com.bignerdranch.android.ktsapplication.api

import com.bignerdranch.android.ktsapplication.api.FullActivity
import com.bignerdranch.android.ktsapplication.auth.Networking

class FullActivityRepository {

    suspend fun getActivityById(
        id: Long,
        include_all_efforts: Boolean
    ) : FullActivity {
        return Networking.stravaApi.getActivityById(id, include_all_efforts)
    }
}