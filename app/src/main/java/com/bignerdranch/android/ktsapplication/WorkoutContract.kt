package com.bignerdranch.android.ktsapplication

object WorkoutContract {
    const val TABLE_NAME = "workouts_table"

    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val DISTANCE = "distance"
        const val KUDOS = "kudos_count"
    }
}