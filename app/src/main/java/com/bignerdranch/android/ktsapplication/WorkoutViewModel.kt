package com.bignerdranch.android.ktsapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WorkoutViewModel : ViewModel() {
    private val workoutRepository = WorkoutRepository()
    private val saveError = Channel<Int>(Channel.BUFFERED)
    private val saveSuccess = Channel<Unit>(Channel.BUFFERED)


    val saveSuccessFlow: Flow<Unit>
        get() = saveSuccess.receiveAsFlow()

    val saveErrorFlow: Flow<Int>
        get() = saveError.receiveAsFlow()

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
            workoutRepository.saveWorkout(workout)
            saveSuccess.send(Unit)
        }
    }
}