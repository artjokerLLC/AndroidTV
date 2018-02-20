package com.smb.presentation.test

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.smb.R
import com.smb.base.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*
import ru.terrakok.cicerone.Navigator

class TestActivity : BaseActivity(), TestView {


    @InjectPresenter
    lateinit var mTestPresenter: TestPresenter

    @ProvidePresenter()
    fun providePresenter() = TestPresenter()

    var dialog: AlertDialog? = null

    override val viewId: Int
        get() = R.layout.activity_test

    override fun onPresenterReady() {
        text.text = mTestPresenter.getUser()
        video_player.setOnClickListener({ _ -> mTestPresenter.loadVideo(this) })
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

    override fun getNavigator(): Navigator? {
        return null
    }

    companion object {
        val TAG = "HomeActivity"

        fun getIntent(context: Context): Intent {

            return Intent(context, TestActivity::class.java)
        }
    }
}
