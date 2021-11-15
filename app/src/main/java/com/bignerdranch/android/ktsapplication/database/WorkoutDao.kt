package com.bignerdranch.android.ktsapplication.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkouts(workouts: List<Workout>)

    @Query("SELECT * FROM ${WorkoutContract.TABLE_NAME}")
    fun observeAllWorkouts(): Flow<List<Workout>>
}