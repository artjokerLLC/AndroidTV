package com.smb.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.smb.navigation.AndroidNavigationLifecycle
import com.smb.navigation.NavigationLifecycle
import ru.terrakok.cicerone.Navigator


/**
 * Created by dev on 13.02.18.
 */

abstract class BaseActivity : MvpAppCompatActivity() {

    protected abstract val viewId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewId)
    }

    override fun onStart() {
        super.onStart()
        onPresenterReady()
    }

    protected abstract fun onPresenterReady()


    override fun onResume() {
        super.onResume()
        getNavigationLifecycle()?.onResume(getNavigator())
    }

    abstract fun getNavigator(): Navigator?

    private fun getNavigationLifecycle(): AndroidNavigationLifecycle? {
        return NavigationLifecycle.getInstance()
    }

    override fun onPause() {
        super.onPause()
        getNavigationLifecycle()?.onPause()
    }
}
