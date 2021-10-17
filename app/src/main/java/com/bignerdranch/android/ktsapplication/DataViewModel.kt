package com.bignerdranch.android.ktsapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    val activityId by lazy {
        MutableLiveData<Long>()
    }
}