package com.smb.presentation.auth

import com.arellomobile.mvp.MvpView

interface AuthView : MvpView {
    fun onUserLoggedIn()
    fun onLoginFailed()
}
