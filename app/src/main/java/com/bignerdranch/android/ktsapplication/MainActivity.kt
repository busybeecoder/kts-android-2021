package com.bignerdranch.android.ktsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import timber.log.Timber


class MainActivity : AppCompatActivity() {


    private lateinit var sActbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        Timber.d("OnCreate MainActivity")

    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart MainActivity")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume MainActivity")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop MainActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("onRestart MainActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy MainActivity")
    }
}