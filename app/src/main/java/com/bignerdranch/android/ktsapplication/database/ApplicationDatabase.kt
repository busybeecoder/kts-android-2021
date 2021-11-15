package com.bignerdranch.android.ktsapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        Workout::class,
        DetailedWorkout::class
    ], version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun detailedWorkoutDao(): DetailedWorkoutDao

    companion object {
        const val DB_NAME = "app-database"
    }
}