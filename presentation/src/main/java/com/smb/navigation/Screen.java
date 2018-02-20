package com.smb.navigation;

/**
 * Created by dev on 19.02.18.
 */

public enum Screen {
    HOME(Constants.HOME),
    MAGIC_HOURS(Constants.MAGIC_HOURS),
    REWARDS(Constants.REWARDS),
    INFLUENCERS(Constants.INFLUENCERS),
    SHOWS(Constants.SHOWS);

    public final String TAG;

    Screen(String tag) {
        TAG = tag;
    }

    public String getTag() {
        return TAG;
    }

    public static class Constants {
        public static final String HOME = "home";
        public static final String MAGIC_HOURS = "magic hours";
        public static final String REWARDS = "rewards";
        public static final String INFLUENCERS = "influencers";
        public static final String SHOWS = "shows";
    }
}
