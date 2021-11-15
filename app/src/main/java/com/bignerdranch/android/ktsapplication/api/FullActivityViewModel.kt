package com.bignerdranch.android.ktsapplication.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FullActivityViewModel : ViewModel() {
    private val repository = FullActivityRepository()
    private val detailedActivityLiveData = MutableLiveData<FullActivity?>()

    private var currentGetDetailedActivityJob: Job? = null

    val fullActivity: MutableLiveData<FullActivity?>
        get() = detailedActivityLiveData

    fun getActivityById(id: Long, include_all_efforts: Boolean) {
        currentGetDetailedActivityJob?.cancel()
        currentGetDetailedActivityJob = viewModelScope.launch {
            runCatching {
                Log.d("tag", "getActivityById()")
                repository.getActivityById(id, include_all_efforts)
            }.onSuccess {
                Log.d("tag", "onSuccess: $it")
                detailedActivityLiveData.postValue(it)
            }.onFailure {
                Log.d("tag", "onFailure: $it")
                detailedActivityLiveData.postValue(null)
            }
        }
    }
}