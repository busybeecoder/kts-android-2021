package com.bignerdranch.android.ktsapplication

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        Workout::class
    ], version = ApplicationDatabase.DB_VERSION
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app-database"
    }
}