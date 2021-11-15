package com.bignerdranch.android.ktsapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = DetailedWorkoutContract.TABLE_NAME,
    indices = [
        Index(DetailedWorkoutContract.Columns.NAME)
    ]
)
data class DetailedWorkout(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DetailedWorkoutContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.DISTANCE)
    val distance: Float,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.TIME)
    val time: Int,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.AVG_SPEED)
    val avgSpeed: Float
)