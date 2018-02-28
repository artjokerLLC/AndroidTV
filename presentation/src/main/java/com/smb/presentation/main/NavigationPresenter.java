package com.smb.presentation.main;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.smb.R;
import com.smb.navigation.Screen;

import static com.smb.navigation.Screen.HOME_TAB;
import static com.smb.navigation.Screen.INFLUENCERS_TAB;
import static com.smb.navigation.Screen.MAGIC_HOURS_TAB;
import static com.smb.navigation.Screen.REWARDS_TAB;
import static com.smb.navigation.Screen.SHOWS_TAB;


public abstract class NavigationPresenter<T extends MvpView> extends MvpPresenter<T> {

    public Screen getScreen(int itemId) {
        Screen screen = null;
        switch (itemId) {
            case R.id.action_home:
                screen = HOME_TAB;
                break;
            case R.id.action_magic_hours:
                screen = MAGIC_HOURS_TAB;
                break;
            case R.id.action_rewards:
                screen = REWARDS_TAB;
                break;
            case R.id.action_influencers:
                screen = INFLUENCERS_TAB;
                break;
            case R.id.action_shows:
                screen = SHOWS_TAB;
                break;
        }
        return screen;
    }

    public abstract void goTo(Screen screen);
}
