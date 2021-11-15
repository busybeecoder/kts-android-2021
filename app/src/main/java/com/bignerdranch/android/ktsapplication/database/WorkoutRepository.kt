package com.bignerdranch.android.ktsapplication.database

import com.bignerdranch.android.ktsapplication.database.Database
import com.bignerdranch.android.ktsapplication.database.Workout
import kotlinx.coroutines.flow.Flow

class WorkoutRepository {

    private val workoutDao = Database.instance.workoutDao()

    suspend fun saveWorkout(workout: Workout) {
        workoutDao.insertWorkouts(listOf(workout))
    }

    fun observeAllWorkouts(): Flow<List<Workout>> {
        return workoutDao.observeAllWorkouts()
    }
}