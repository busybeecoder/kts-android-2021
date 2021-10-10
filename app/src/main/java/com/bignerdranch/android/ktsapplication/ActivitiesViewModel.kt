package com.bignerdranch.android.ktsapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ActivitiesViewModel : ViewModel() {
    private val repository = ActivitiesRepository()
    private val activitiesLiveData = MutableLiveData<List<Activities>>(emptyList())
    private val isLoadingLiveData = MutableLiveData(false)

    private var getActivitiesJob: Job? = null

    val activitiesList: LiveData<List<Activities>>
        get() = activitiesLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun getListActivities(before: Int, after: Int) {
        isLoadingLiveData.postValue(true)
        getActivitiesJob?.cancel()
        getActivitiesJob = viewModelScope.launch {
            runCatching {
                Log.d("tag", "getAthletes()")
                repository.getAthleteActivities(before, after)
            }.onSuccess {
                Log.d("tag", "onSuccess: $it")
                isLoadingLiveData.postValue(false)
                activitiesLiveData.postValue(it)
            }.onFailure {
                Log.d("tag", "onFailure: $it")
                isLoadingLiveData.postValue(false)
                activitiesLiveData.postValue(emptyList())
            }
        }
    }
}