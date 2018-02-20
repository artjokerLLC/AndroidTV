package com.smb.presentation.test

import android.content.Intent
import com.arellomobile.mvp.MvpView

interface TestView : MvpView {
    fun showProgress()
    fun hideProgress()
    fun startNewActivity(intent: Intent)
}
