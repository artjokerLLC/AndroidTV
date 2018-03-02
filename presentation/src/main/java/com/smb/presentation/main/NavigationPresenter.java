package com.smb.presentation.main;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.smb.navigation.Screen;

import static com.smb.navigation.Screen.HOME_TAB;
import static com.smb.navigation.Screen.INFLUENCERS_TAB;
import static com.smb.navigation.Screen.MAGIC_HOURS_TAB;
import static com.smb.navigation.Screen.REWARDS_TAB;
import static com.smb.navigation.Screen.SHOWS_TAB;


public abstract class NavigationPresenter<T extends MvpView> extends MvpPresenter<T> {

    public Screen getScreen(int position) {
        Screen screen = null;
        switch (position) {
            case 0:
                screen = HOME_TAB;
                break;
            case 1:
                screen = MAGIC_HOURS_TAB;
                break;
            case 2:
                screen = REWARDS_TAB;
                break;
            case 3:
                screen = INFLUENCERS_TAB;
                break;
            case 4:
                screen = SHOWS_TAB;
                break;
        }
        return screen;
    }

    public abstract void goTo(Screen screen);

    public abstract void root(Screen homeFragment);
}
