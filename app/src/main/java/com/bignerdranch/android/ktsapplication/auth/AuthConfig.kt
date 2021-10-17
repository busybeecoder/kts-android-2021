package com.bignerdranch.android.ktsapplication.auth

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://www.strava.com/oauth/mobile/authorize"
    const val TOKEN_URI = "https://www.strava.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "activity:read_all"

    const val CLIENT_ID = "72852"
    const val CLIENT_SECRET = "9e3e8ed5108c31fe9d7fc2b8a80a2d9ad79c98a2"
    const val CALLBACK_URL = "school://kts.studio/callback"
}