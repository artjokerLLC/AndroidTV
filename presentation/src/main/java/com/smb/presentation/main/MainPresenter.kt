package com.smb.presentation.main


import android.app.Activity
import android.content.Context
import android.content.Intent
import com.apollographql.apollo.api.Response
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.smb.data.authentication.SocialNetworkProvider
import com.smb.data.authentication.SocialNetworkType
import com.smb.data.repositories.api.LocalUserRepository
import com.smb.data.repositories.api.RemoteUserRepository
import com.smb.di.DependencyContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var socialNetworkProvider: SocialNetworkProvider
    @Inject
    lateinit var remoteUserRepository: RemoteUserRepository
    @Inject
    lateinit var localUserRepository: LocalUserRepository

    override fun attachView(view: MainView?) {
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
}
