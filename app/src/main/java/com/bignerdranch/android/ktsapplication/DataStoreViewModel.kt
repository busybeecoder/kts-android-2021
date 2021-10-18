package com.bignerdranch.android.ktsapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ktsapplication.auth.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DataStoreViewModel(
    application:  Application
) : AndroidViewModel(application) {

    private val repository = UserPreferences(application)

    val isLaunchedLiveData: Flow<Boolean>
        get() = repository.observe()

    fun save(isLaunched: Boolean) {
        viewModelScope.launch {
            repository.save(isLaunched)
        }
    }
}