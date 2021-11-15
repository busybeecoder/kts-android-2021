package com.bignerdranch.android.ktsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.ktsapplication.auth.Networking
import com.bignerdranch.android.ktsapplication.database.Database
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Networking.init(this)
        Database.init(this)
        setSupportActionBar(topAppBar)
    }
}