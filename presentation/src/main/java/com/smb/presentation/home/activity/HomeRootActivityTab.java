package com.smb.presentation.home.activity;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.R;
import com.smb.navigation.Screen;
import com.smb.presentation.main.NavigationPresenter;
import com.smb.presentation.main.TabNavigationActivity;

public class HomeRootActivityTab extends TabNavigationActivity implements HomeRootView {
    public static final String TAG = "HomeActivity";
    @InjectPresenter
    HomeRootPresenter mHomeRootPresenter;

    public static Intent getIntent(final Context context) {

        Intent intent = new Intent(context, HomeRootActivityTab.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        return intent;
    }


    @Override
    public NavigationPresenter getNavigationPresenter() {
        return mHomeRootPresenter;
    }

    @Override
    protected void onPresenterReady() {
        super.onPresenterReady();
        mHomeRootPresenter.root(Screen.HOME_FRAGMENT);
    }

    @Override
    protected int getTabId() {
        return R.id.action_home;
    }
}
