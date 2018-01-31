package com.smb.presentation


import android.app.Activity
import android.content.Context
import android.content.Intent
import com.apollographql.apollo.api.Response
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.smb.data.authentication.SocialNetworkProvider
import com.smb.data.authentication.SocialNetworkType
import com.smb.data.repositories.RemoteUserRepository
import com.smb.di.DependencyContainer
import guest.RegisterMutation
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var socialNetworkProvider: SocialNetworkProvider
    @Inject
    lateinit var remoteUserRepository: RemoteUserRepository

    override fun attachView(view: MainView?) {
        super.attachView(view)
        DependencyContainer.getAppComponent().inject(this)
    }

    fun loginBy(networkType: SocialNetworkType, activity: Activity) {
        socialNetworkProvider.loginBy(networkType, activity)
                .flatMap { socialData -> remoteUserRepository.getUser(socialData) }
                .subscribe()
    }

    private fun onErrorHandled(result: Response<RegisterMutation.Data>?) {

    }

    private fun onUserRegistered() {

    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        socialNetworkProvider.onActivityResult(requestCode, resultCode, data)
    }
}
