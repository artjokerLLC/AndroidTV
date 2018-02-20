package com.smb.presentation.auth


import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.smb.R
import com.smb.base.BaseActivity
import com.smb.data.authentication.SocialNetworkType.*
import com.smb.presentation.home.activity.HomeRootActivity
import kotlinx.android.synthetic.main.activity_auth.*
import ru.terrakok.cicerone.Navigator

class AuthActivity : BaseActivity(), AuthView {


    override val viewId: Int
        get() = R.layout.activity_auth

    @InjectPresenter
    lateinit var mAuthPresenter: AuthPresenter

    @ProvidePresenter()
    fun providePresenter() = AuthPresenter()

    override fun onPresenterReady() {
        mAuthPresenter.checkAuthorization()
        loginByFacebookButton.setOnClickListener { _ -> mAuthPresenter.loginBy(FACEBOOK, this) }
        loginByTwitterButton.setOnClickListener { _ -> mAuthPresenter.loginBy(TWITTER, this) }
        loginByInstagramButton.setOnClickListener { _ -> mAuthPresenter.loginBy(INSTAGRAM, this) }
        loginByGoogleButton.setOnClickListener { _ -> mAuthPresenter.loginBy(GOOGLE, this) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mAuthPresenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun onLoginFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
    }

    override fun onUserLoggedIn() {
        // startActivity(TestActivity.getIntent(this))
        startActivity(HomeRootActivity.getIntent(this))
    }

    override fun getNavigator(): Navigator? {
        return null
    }

    companion object {
        val TAG = "BlankActivity"

        fun getIntent(context: Context): Intent {

            return Intent(context, AuthActivity::class.java)
        }
    }
}
