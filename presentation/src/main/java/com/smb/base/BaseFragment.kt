package com.smb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by dev on 20.02.18.
 */

abstract class BaseFragment : MvpAppCompatFragment() {

    protected abstract val viewId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(viewId, container, false)
    }

    override fun onStart() {
        super.onStart()
        onPresenterReady()
    }

    abstract fun onPresenterReady()
}
