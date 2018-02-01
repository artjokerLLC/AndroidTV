package com.smb.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.smb.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : MvpActivity(), HomeView {
    @InjectPresenter
    lateinit var mHomePresenter: HomePresenter

    @ProvidePresenter()
    fun providePresenter() = HomePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onStart() {
        super.onStart()
        text.text = mHomePresenter.getUser()
    }

    companion object {
        val TAG = "HomeActivity"

        fun getIntent(context: Context): Intent {

            return Intent(context, HomeActivity::class.java)
        }
    }
}
