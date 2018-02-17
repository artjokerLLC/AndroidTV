package com.smb.base

import android.os.Bundle
import com.arellomobile.mvp.MvpActivity

/**
 * Created by dev on 13.02.18.
 */

abstract class BaseActivity : MvpActivity() {

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
}
