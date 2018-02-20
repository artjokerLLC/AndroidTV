package com.smb.presentation.main;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.smb.R;
import com.smb.navigation.Screen;

import static com.smb.navigation.Screen.HOME;
import static com.smb.navigation.Screen.INFLUENCERS;
import static com.smb.navigation.Screen.MAGIC_HOURS;
import static com.smb.navigation.Screen.REWARDS;
import static com.smb.navigation.Screen.SHOWS;


public abstract class NavigationPresenter<T extends MvpView> extends MvpPresenter<T> {

    public Screen getScreen(int itemId) {
        Screen screen = null;
        switch (itemId) {
            case R.id.action_home:
                screen = HOME;
                break;
            case R.id.action_magic_hours:
                screen = MAGIC_HOURS;
                break;
            case R.id.action_rewards:
                screen = REWARDS;
                break;
            case R.id.action_influencers:
                screen = INFLUENCERS;
                break;
            case R.id.action_shows:
                screen = SHOWS;
                break;
        }
        return screen;
    }

    public abstract void goTo(Screen screen);
}
