package com.smb.presentation.home

import android.content.Intent
import com.arellomobile.mvp.MvpView

interface HomeView : MvpView {
    fun showProgress()
    fun hideProgress()
    fun startNewActivity(intent: Intent)
}
