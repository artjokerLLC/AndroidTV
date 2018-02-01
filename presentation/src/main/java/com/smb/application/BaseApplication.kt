package com.smb.application

import android.app.Application

import com.smb.di.DependencyContainer
import com.tspoon.traceur.Traceur

/**
 * Created by dev on 26.01.18.
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Traceur.enableLogging()
        DependencyContainer.inject(this)
    }
}
