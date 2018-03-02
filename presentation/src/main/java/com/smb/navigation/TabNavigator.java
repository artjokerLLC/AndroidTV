package com.smb.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.smb.presentation.home.HomeFragment;
import com.smb.presentation.home.activity.HomeRootActivityTab;
import com.smb.presentation.influencers.InfluencersFragment;
import com.smb.presentation.influencers.activity.InfluencersRootActivityTab;
import com.smb.presentation.shows.ShowsFragment;
import com.smb.presentation.shows.activity.ShowsRootActivityTab;

import ru.terrakok.cicerone.android.SupportAppNavigator;

/**
 * Created by dev on 28.02.18.
 */

public class TabNavigator extends SupportAppNavigator {

    public TabNavigator(FragmentActivity activity, int containerId) {
        super(activity, containerId);
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {

            case Screen.Constants.Fragments.HOME_FRAGMENT:
                return HomeFragment.Companion.newInstance();

            case Screen.Constants.Fragments.SHOWS_FRAGMENT:
                return ShowsFragment.newInstance();

            case Screen.Constants.Fragments.INFLUENCERS_FRAGMENT:
                return InfluencersFragment.newInstance();

            default:
                return null;
        }

    }

    @Override
    protected Intent createActivityIntent(Context context, String screenKey, Object data) {
        switch (screenKey) {
            case Screen.Constants.Tabs.HOME_TAB:
                return HomeRootActivityTab.getIntent(context);

            case Screen.Constants.Tabs.SHOWS_TAB:
                return ShowsRootActivityTab.getIntent(context);

            case Screen.Constants.Tabs.INFLUENCERS_TAB:
                return InfluencersRootActivityTab.getIntent(context);

            default:
                return null;
        }

    }
}
