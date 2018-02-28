package com.smb.presentation.shows.activity;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.R;
import com.smb.navigation.Screen;
import com.smb.presentation.main.NavigationPresenter;
import com.smb.presentation.main.TabNavigationActivity;

public class ShowsRootActivityTab extends TabNavigationActivity implements ShowsView {
    public static final String TAG = "ShowsActivity";
    @InjectPresenter
    ShowsRootPresenter mShowsRootPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, ShowsRootActivityTab.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        return intent;
    }


    @Override
    public NavigationPresenter getNavigationPresenter() {
        return mShowsRootPresenter;
    }

    @Override
    protected void onPresenterReady() {
        super.onPresenterReady();
        mShowsRootPresenter.goTo(Screen.SHOWS_FRAGMENT);
    }

    @Override
    protected int getTabId() {
        return R.id.action_shows;
    }
}
