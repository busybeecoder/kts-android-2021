package com.bignerdranch.android.ktsapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.android.ktsapplication.database.DetailedWorkout
import com.bignerdranch.android.ktsapplication.database.DetailedWorkoutContract

@Dao
interface DetailedWorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailedWorkout(detailedWorkout: DetailedWorkout)

    @Query("SELECT * FROM ${DetailedWorkoutContract.TABLE_NAME} WHERE ${DetailedWorkoutContract.Columns.ID} = :id")
    suspend fun getDetailedWorkoutById(id: Long): DetailedWorkout?
}