package com.smb.presentation.home

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.gson.Gson
import com.smb.data.repositories.api.LocalUserRepository
import com.smb.di.DependencyContainer
import javax.inject.Inject

@InjectViewState
class HomePresenter : MvpPresenter<HomeView>() {
    @Inject
    lateinit var localUserRepository: LocalUserRepository

    override fun attachView(view: HomeView?) {
        super.attachView(view)
        DependencyContainer.getAppComponent().inject(this)
    }

    fun getUser(): String {
        return Gson().toJson(localUserRepository.get())
    }
}
