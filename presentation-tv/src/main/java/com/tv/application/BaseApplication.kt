package com.tv.application

import android.app.Application

import com.tv.di.DependencyContainer

/**
 * Created by dev on 26.01.18.
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyContainer.inject(this)
    }
}
