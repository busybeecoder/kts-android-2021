package com.bignerdranch.android.ktsapplication

import kotlinx.coroutines.flow.Flow

class WorkoutRepository {

    private val workoutDao = Database.instance.workoutDao()

    suspend fun saveWorkout(workout: Workout) {
        workoutDao.insertWorkouts(listOf(workout))
    }

    suspend fun updateWorkout(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    suspend fun removeWorkout(workoutId: Long) {
        workoutDao.removeWorkoutById(workoutId)
    }

    suspend fun getAllWorkouts(): List<Workout> {
        return workoutDao.getAllWorkouts()
    }

    fun observeAllWorkouts(): Flow<List<Workout>> {
        return workoutDao.observeAllWorkouts()
    }
}