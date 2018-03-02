package com.smb.base

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by dev on 20.02.18.
 */

abstract class BaseFragment : MvpAppCompatFragment() {
    var dialog: AlertDialog? = null
    protected abstract val viewId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(viewId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onPresenterReady()
    }

    fun showProgress() {
        dialog?.dismiss()
        dialog = activity?.let {
            AlertDialog.Builder(it)
                    .setCancelable(false)
                    .setMessage("Loading")
                    .create()
        }
        dialog?.show()
    }

    fun hideProgress() {
        dialog?.dismiss()
        dialog = null
    }
    abstract fun onPresenterReady()
}
