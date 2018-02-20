package com.smb.presentation.home.activity;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.presentation.main.NavigationActivity;
import com.smb.presentation.main.NavigationPresenter;

public class HomeRootActivity extends NavigationActivity implements HomeRootView {
    public static final String TAG = "HomeActivity";
    @InjectPresenter
    HomeRootPresenter mHomeRootPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, HomeRootActivity.class);

        return intent;
    }

    @Override
    public NavigationPresenter getNavigationPresenter() {
        return mHomeRootPresenter;
    }


}
