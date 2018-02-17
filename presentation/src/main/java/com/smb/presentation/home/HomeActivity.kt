package com.smb.presentation.home

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.smb.R
import com.smb.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), HomeView {

    @InjectPresenter
    lateinit var mHomePresenter: HomePresenter

    @ProvidePresenter()
    fun providePresenter() = HomePresenter()

    var dialog: AlertDialog? = null

    override val viewId: Int
        get() = R.layout.activity_home

    override fun onPresenterReady() {
        text.text = mHomePresenter.getUser()
        video_player.setOnClickListener({ _ -> mHomePresenter.loadVideo(this) })
    }

    override fun hideProgress() {
        dialog?.dismiss()
        dialog = null
    }

    override fun showProgress() {
        dialog?.dismiss()
        dialog = AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Loading")
                .create()
        dialog?.show()
    }

    override fun startNewActivity(intent: Intent) {
        startActivity(intent)
    }

    companion object {
        val TAG = "HomeActivity"

        fun getIntent(context: Context): Intent {

            return Intent(context, HomeActivity::class.java)
        }
    }
}
