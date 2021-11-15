package com.bignerdranch.android.ktsapplication.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ktsapplication.database.Workout
import com.bignerdranch.android.ktsapplication.database.WorkoutRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class WorkoutViewModel : ViewModel() {
    private val workoutRepository = WorkoutRepository()
    private val saveError = Channel<String>(Channel.BUFFERED)
    private val saveSuccess = Channel<Unit>(Channel.BUFFERED)

    fun save(
        id: Long,
        name: String,
        distance: Float,
        kudos: Int
    ) {

        val workout = Workout(
            id = id,
            name = name,
            distance = distance,
            kudos = kudos
        )

        viewModelScope.launch {
            try {
                workoutRepository.saveWorkout(workout)
                saveSuccess.send(Unit)
            } catch (t: Throwable) {
                saveError.send("workout save error")
            }
        }
    }
}