package com.smb.application

import android.app.Application
import android.support.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.smb.di.DependencyContainer
import com.tspoon.traceur.Traceur
import io.fabric.sdk.android.Fabric

/**
 * Created by dev on 26.01.18.
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Fabric.with(this, Crashlytics())
        Traceur.enableLogging()
        DependencyContainer.inject(this)
    }
}
