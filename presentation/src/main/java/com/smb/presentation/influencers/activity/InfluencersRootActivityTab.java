package com.smb.presentation.influencers.activity;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.navigation.Screen;
import com.smb.presentation.main.NavigationPresenter;
import com.smb.presentation.main.TabNavigationActivity;

public class InfluencersRootActivityTab extends TabNavigationActivity implements InfluencersRootView {
    public static final String TAG = "InfluencersRootActivity";
    @InjectPresenter
    InfluencersRootPresenter mInfluencersRootPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, InfluencersRootActivityTab.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        return intent;
    }

    @Override
    protected int getTabPosition() {
        return 3;
    }

    @Override
    protected void onPresenterReady() {
        super.onPresenterReady();
        mInfluencersRootPresenter.root(Screen.INFLUENCERS_FRAGMENT);
    }

    @Override
    public NavigationPresenter getNavigationPresenter() {
        return mInfluencersRootPresenter;
    }
}
