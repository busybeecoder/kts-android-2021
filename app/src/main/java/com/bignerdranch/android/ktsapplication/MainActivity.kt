package com.bignerdranch.android.ktsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.ktsapplication.auth.Networking


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Networking.init(this)
    }
}