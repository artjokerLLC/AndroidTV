package com.smb.presentation


import android.app.Activity
import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.smb.data.authentication.SocialNetworkProvider
import com.smb.data.authentication.SocialNetworkType
import com.smb.data.repositories.TokenRepository
import com.smb.di.DependencyContainer
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var socialNetworkProvider: SocialNetworkProvider
    @Inject
    lateinit var tokenRepository: TokenRepository

    override fun attachView(view: MainView?) {
        super.attachView(view)
        DependencyContainer.getAppComponent().inject(this)
    }

    fun loginBy(networkType: SocialNetworkType, activity: Activity) {
        socialNetworkProvider.loginBy(networkType, activity)
                .flatMap { socialData -> tokenRepository.authorize(socialData) }
                .subscribe({ viewState.onSocialLoginResult() }, { viewState.onSocialLoginError() })
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        socialNetworkProvider.onActivityResult(requestCode, resultCode, data)
    }
}
