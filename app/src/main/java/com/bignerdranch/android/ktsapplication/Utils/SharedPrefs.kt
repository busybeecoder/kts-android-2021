package com.bignerdranch.android.ktsapplication.Utils

import android.content.Context

class SharedPrefs(context: Context) {
    private val sharedPrefs = context.getSharedPreferences("Auth", Context.MODE_PRIVATE)

    var token = sharedPrefs.getString("token", null)

    val refreshToken = sharedPrefs.getString("refresh_token", null)

    fun saveString(value: String, key: String) {
        sharedPrefs.edit()
            .putString(key, value)
            .apply()
    }
}
