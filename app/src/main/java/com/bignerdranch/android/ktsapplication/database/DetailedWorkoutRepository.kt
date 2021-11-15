package com.bignerdranch.android.ktsapplication.database

import com.bignerdranch.android.ktsapplication.database.Database
import com.bignerdranch.android.ktsapplication.database.DetailedWorkout

class DetailedWorkoutRepository {

    private val detailedWorkoutDao = Database.instance.detailedWorkoutDao()

    suspend fun saveWorkout(detailedWorkout: DetailedWorkout) {
        detailedWorkoutDao.insertDetailedWorkout(detailedWorkout)
    }

    suspend fun getDetailedWorkoutById(id: Long): DetailedWorkout? {
        return detailedWorkoutDao.getDetailedWorkoutById(id)
    }
}