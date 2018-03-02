package com.smb.navigation;

import com.smb.navigation.Screen.Constants.Fragments;
import com.smb.navigation.Screen.Constants.Tabs;

/**
 * Created by dev on 19.02.18.
 */

public enum Screen {

    HOME_TAB(Tabs.HOME_TAB),
    MAGIC_HOURS_TAB(Tabs.MAGIC_HOURS_TAB),
    REWARDS_TAB(Tabs.REWARDS_TAB),
    INFLUENCERS_TAB(Tabs.INFLUENCERS_TAB),
    SHOWS_TAB(Tabs.SHOWS_TAB),

    HOME_FRAGMENT(Fragments.HOME_FRAGMENT),
    SHOWS_FRAGMENT(Fragments.SHOWS_FRAGMENT),
    INFLUENCERS_FRAGMENT(Fragments.INFLUENCERS_FRAGMENT);

    public final String TAG;

    Screen(String tag) {
        TAG = tag;
    }

    public String getTag() {
        return TAG;
    }

    public static class Constants {

        public interface Tabs {
            String HOME_TAB = "home tab";
            String MAGIC_HOURS_TAB = "magic hours tab";
            String REWARDS_TAB = "rewards tab";
            String INFLUENCERS_TAB = "influencers tab";
            String SHOWS_TAB = "shows tab";
        }

        public interface Fragments {
            String HOME_FRAGMENT = "home fragment";
            String SHOWS_FRAGMENT = "shows fragment";
            String INFLUENCERS_FRAGMENT = "influencers fragment";
        }
    }
}
