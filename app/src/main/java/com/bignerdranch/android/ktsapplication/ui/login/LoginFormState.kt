package com.bignerdranch.android.ktsapplication.ui.login

import androidx.annotation.StringRes

data class LoginFormState(
    @StringRes val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)