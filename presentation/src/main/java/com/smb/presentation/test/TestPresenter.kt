package com.smb.presentation.test

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.gson.Gson
import com.smb.core.repositories.LocalUserRepository
import com.smb.data.repositories.shows.TestShowsRepository
import com.smb.di.DependencyContainer
import com.smb.presentation.player.PlayerActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class TestPresenter : MvpPresenter<TestView>() {
    @Inject
    lateinit var localUserRepository: LocalUserRepository
    @Inject
    lateinit var testShowsRepository: TestShowsRepository

    override fun attachView(view: TestView?) {
        super.attachView(view)
        DependencyContainer.getAppComponent().inject(this)
    }

    fun getUser(): String {
        return Gson().toJson(localUserRepository.get())
    }

    fun loadVideo(context: Context) {
        viewState.showProgress()
        testShowsRepository.randomHlsLink
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { viewState.hideProgress() }
                .map { link -> PlayerActivity.getIntent(context, link) }
                .subscribe { intent -> viewState.startNewActivity(intent) }
    }
}
