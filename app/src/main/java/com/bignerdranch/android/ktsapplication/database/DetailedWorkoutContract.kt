package com.bignerdranch.android.ktsapplication.database

object DetailedWorkoutContract {
    const val TABLE_NAME = "detailedWorkouts"

    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val DISTANCE = "distance"
        const val TIME = "time"
        const val AVG_SPEED = "avgSpeed"
    }
}