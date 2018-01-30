package com.smb.application

import android.app.Application

import com.smb.di.DependencyContainer

/**
 * Created by dev on 26.01.18.
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyContainer.inject(this)
    }
}
