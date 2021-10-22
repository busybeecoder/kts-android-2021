package com.bignerdranch.android.ktsapplication

import android.content.Context

class SharedPrefs(context: Context) {
    private val sharedPrefs = context.getSharedPreferences("Auth", Context.MODE_PRIVATE)

    val token = sharedPrefs.getString("token", null)

    val refreshToken = sharedPrefs.getString("refresh_token", null)

    fun saveString(value: String, key: String) {
        sharedPrefs.edit()
            .putString(key, value)
            .apply()
    }
}
