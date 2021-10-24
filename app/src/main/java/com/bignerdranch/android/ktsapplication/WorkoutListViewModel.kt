package com.bignerdranch.android.ktsapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class WorkoutListViewModel : ViewModel() {
    private val workoutRepository = WorkoutRepository()

    val workoutsFlow: Flow<List<Workout>>
        get() = workoutRepository.observeAllWorkouts()
}