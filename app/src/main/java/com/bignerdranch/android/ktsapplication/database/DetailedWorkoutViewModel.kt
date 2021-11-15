package com.bignerdranch.android.ktsapplication.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ktsapplication.api.FullActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailedWorkoutViewModel : ViewModel() {
    private val detailedWorkoutRepository = DetailedWorkoutRepository()
    private val detailedWorkoutEmpty = DetailedWorkout(0, "", 0F, 0, 0F)
    private val workoutMutableFlow = MutableStateFlow<DetailedWorkout?>(detailedWorkoutEmpty)
    private val saveSuccess = Channel<Unit>(Channel.BUFFERED)
    private val saveError = Channel<String>(Channel.BUFFERED)
    val workoutFlow: Flow<DetailedWorkout?>
        get() = workoutMutableFlow.asStateFlow()

    fun save(
        id: Long,
        fullActivity: FullActivity
    ) {

        val detailedWorkout = DetailedWorkout(
            id = id,
            name = fullActivity.name,
            distance = fullActivity.distance,
            time = fullActivity.time,
            avgSpeed = fullActivity.avgSpeed,
        )
        viewModelScope.launch {
            try {
                detailedWorkoutRepository.saveWorkout(detailedWorkout)
                saveSuccess.send(Unit)
            } catch (t: Throwable) {
                saveError.send("detailedWorkout save error")
            }
        }
    }

    fun loadWorkoutById(id: Long) {
        viewModelScope.launch {
            workoutMutableFlow.value = detailedWorkoutRepository.getDetailedWorkoutById(id)
        }
    }
}