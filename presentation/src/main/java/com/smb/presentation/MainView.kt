package com.smb.presentation

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun onSocialLoginResult(result: Any)
}
