package com.bignerdranch.android.ktsapplication

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkouts(workouts: List<Workout>)

    @Query("SELECT * FROM ${WorkoutContract.TABLE_NAME}")
    suspend fun getAllWorkouts(): List<Workout>

    @Query("SELECT * FROM ${WorkoutContract.TABLE_NAME}")
    fun observeAllWorkouts(): Flow<List<Workout>>

    @Update
    suspend fun updateWorkout(workout: Workout)

    @Delete
    suspend fun removeWorkout(workout: Workout)

    @Query("DELETE FROM ${WorkoutContract.TABLE_NAME} WHERE ${WorkoutContract.Columns.ID} = :workoutId")
    suspend fun removeWorkoutById(workoutId: Long)

    @Query("DELETE FROM ${WorkoutContract.TABLE_NAME}")
    suspend fun removeAll()
}