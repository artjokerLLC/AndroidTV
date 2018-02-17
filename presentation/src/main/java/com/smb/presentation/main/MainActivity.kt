package com.smb.presentation.main


import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.smb.R
import com.smb.base.BaseActivity
import com.smb.data.authentication.SocialNetworkType.*
import com.smb.presentation.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {


    override val viewId: Int
        get() = R.layout.activity_main

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    @ProvidePresenter()
    fun providePresenter() = MainPresenter()

    override fun onPresenterReady() {
        mMainPresenter.checkAuthorization()
        login_by_facebook.setOnClickListener { _ -> mMainPresenter.loginBy(FACEBOOK, this) }
        login_by_twitter.setOnClickListener { _ -> mMainPresenter.loginBy(TWITTER, this) }
        login_by_instagram.setOnClickListener { _ -> mMainPresenter.loginBy(INSTAGRAM, this) }
        login_by_google.setOnClickListener { _ -> mMainPresenter.loginBy(GOOGLE, this) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mMainPresenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun onLoginFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
    }

    override fun onUserLoggedIn() {
        startActivity(HomeActivity.getIntent(this))
    }


    companion object {
        val TAG = "BlankActivity"

        fun getIntent(context: Context): Intent {

            return Intent(context, MainActivity::class.java)
        }
    }
}
