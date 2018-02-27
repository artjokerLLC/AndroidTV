package com.smb.presentation.main;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import com.smb.R;
import com.smb.base.BaseActivity;
import com.smb.navigation.Screen;
import com.smb.presentation.home.HomeFragment;

import org.jetbrains.annotations.Nullable;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportAppNavigator;

public abstract class NavigationActivity extends BaseActivity {
    public static final String TAG = "NavigationActivity";

    @Override
    protected int getViewId() {
        return R.layout.activity_navigation;
    }

    @Override
    protected void onPresenterReady() {
        BottomNavigationView navigationMenu = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(navigationMenu);

        navigationMenu.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            NavigationPresenter navigationPresenter = getNavigationPresenter();
            Screen screen = navigationPresenter.getScreen(itemId);
            navigationPresenter.goTo(screen);
            return true;
        });
        navigationMenu.setSelectedItemId(R.id.action_home);
    }

    public abstract NavigationPresenter getNavigationPresenter();


    @Nullable
    @Override
    public Navigator getNavigator() {
        return new SupportAppNavigator(this, R.id.container) {
            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                switch (screenKey) {

                    case Screen.Constants.HOME:
                        return HomeFragment.Companion.newInstance();

                    default:
                        return HomeFragment.Companion.newInstance();
                }

            }

            @Override
            protected Intent createActivityIntent(Context context, String screenKey, Object data) {

                return null;
            }
        };
    }
}
