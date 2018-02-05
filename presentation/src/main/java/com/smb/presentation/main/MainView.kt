package com.smb.presentation.main

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun onUserLoggedIn()
    fun onLoginFailed()
}
