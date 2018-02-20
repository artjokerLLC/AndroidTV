package com.smb.presentation.auth


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.smb.core.repositories.LocalUserRepository
import com.smb.core.repositories.RemoteUserRepository
import com.smb.data.authentication.SocialNetworkProvider
import com.smb.data.authentication.SocialNetworkType
import com.smb.di.DependencyContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class AuthPresenter : MvpPresenter<AuthView>() {
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var socialNetworkProvider: SocialNetworkProvider
    @Inject
    lateinit var remoteUserRepository: RemoteUserRepository
    @Inject
    lateinit var localUserRepository: LocalUserRepository

    override fun attachView(view: AuthView?) {
        super.attachView(view)
        DependencyContainer.getAppComponent().inject(this)
    }

    fun loginBy(networkType: SocialNetworkType, activity: Activity) {
        socialNetworkProvider.loginBy(networkType, activity)
                .flatMap { socialData -> remoteUserRepository.getUser(socialData) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    localUserRepository.put(user)
                    viewState.onUserLoggedIn()
                }, { error ->
                    error.printStackTrace()
                    viewState.onLoginFailed()
                })
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        socialNetworkProvider.onActivityResult(requestCode, resultCode, data)
    }

    fun checkAuthorization() {
        val user = localUserRepository.get()
        if (!TextUtils.isEmpty(user?.token)) {
            viewState.onUserLoggedIn()
        }
    }
}
