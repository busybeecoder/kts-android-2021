package com.bignerdranch.android.ktsapplication.auth

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AddTokenHeaderInterceptor(context: Context) : Interceptor {

    private val sharedPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE)
    private val token = sharedPreferences.getString("token", null)

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        if (token != null) {
            original = original.newBuilder()
                .header("Authorization", "Bearer $token")
                .method(original.method, original.body)
                .build()
        }
        return chain.proceed(original)
    }
}