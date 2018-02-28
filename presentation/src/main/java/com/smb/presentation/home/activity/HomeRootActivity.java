package com.smb.presentation.home.activity;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.R;
import com.smb.presentation.main.NavigationActivity;
import com.smb.presentation.main.NavigationPresenter;

public class HomeRootActivity extends NavigationActivity implements HomeRootView {
    public static final String TAG = "HomeActivity";
    @InjectPresenter
    HomeRootPresenter mHomeRootPresenter;

    public static Intent getIntent(final Context context) {

        return new Intent(context, HomeRootActivity.class);
    }

    @Override
    public NavigationPresenter getNavigationPresenter() {
        return mHomeRootPresenter;
    }

    @Override
    protected void onPresenterReady() {
        getNavigationMenu().setSelectedItemId(R.id.action_home);
        super.onPresenterReady();

    }
}
