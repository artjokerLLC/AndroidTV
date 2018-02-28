package com.smb.presentation.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import com.smb.R;
import com.smb.base.BaseActivity;
import com.smb.navigation.Screen;
import com.smb.presentation.home.HomeFragment;
import com.smb.presentation.home.activity.HomeRootActivity;
import com.smb.presentation.shows.ShowsRootActivity;

import org.jetbrains.annotations.Nullable;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportAppNavigator;

public abstract class NavigationActivity extends BaseActivity {
    public static final String TAG = "NavigationActivity";
    private BottomNavigationView navigationMenu;

    @Override
    protected int getViewId() {
        return R.layout.activity_navigation;
    }

    public BottomNavigationView getNavigationMenu() {
        return navigationMenu;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationMenu = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(navigationMenu);
    }

    @Override
    protected void onPresenterReady() {


        navigationMenu.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            NavigationPresenter navigationPresenter = getNavigationPresenter();
            Screen screen = navigationPresenter.getScreen(itemId);
            navigationPresenter.goTo(screen);
            return true;
        });
    }

    public abstract NavigationPresenter getNavigationPresenter();


    @Nullable
    @Override
    public Navigator getNavigator() {
        return new SupportAppNavigator(this, R.id.container) {
            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                switch (screenKey) {

                    case Screen.Constants.Fragments.HOME_FRAGMENT:
                        return HomeFragment.Companion.newInstance();

                    default:
                        return null;
                }

            }

            @Override
            protected Intent createActivityIntent(Context context, String screenKey, Object data) {
                switch (screenKey) {
                    case Screen.Constants.Tabs.HOME_TAB:
                        return HomeRootActivity.getIntent(context);
                    case Screen.Constants.Tabs.SHOWS_TAB:
                        return ShowsRootActivity.getIntent(context);
                    default:
                        return null;
                }

            }
        };
    }
}
