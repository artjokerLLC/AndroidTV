package com.smb.presentation.main;

import android.support.v4.content.ContextCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.smb.R;
import com.smb.base.BaseActivity;
import com.smb.navigation.Screen;
import com.smb.navigation.TabNavigator;

import org.jetbrains.annotations.Nullable;

import ru.terrakok.cicerone.Navigator;

public abstract class TabNavigationActivity extends BaseActivity {
    public static final String TAG = "NavigationActivity";
    private AHBottomNavigation navigationMenu;

    @Override
    protected int getViewId() {
        return R.layout.activity_navigation;
    }

    public AHBottomNavigation getNavigationMenu() {
        return navigationMenu;
    }

    @Override
    protected void onPresenterReady() {
        navigationMenu = findViewById(R.id.bottom_navigation);
        // BottomNavigationViewHelper.removeShiftMode(navigationMenu);
        navigationMenu.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        navigationMenu.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        navigationMenu.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        AHBottomNavigationItem home = new AHBottomNavigationItem(R.string.home, R.drawable.home_selector, android.R.color.white);
        AHBottomNavigationItem magicHours = new AHBottomNavigationItem(R.string.magic_hours, R.drawable.home_selector, android.R.color.white);
        AHBottomNavigationItem rewards = new AHBottomNavigationItem(R.string.rewards, R.drawable.home_selector, android.R.color.white);
        AHBottomNavigationItem influencers = new AHBottomNavigationItem(R.string.influencers, R.drawable.home_selector, android.R.color.white);
        AHBottomNavigationItem shows = new AHBottomNavigationItem(R.string.shows, R.drawable.shows_selector, android.R.color.white);

        navigationMenu.addItem(home);
        navigationMenu.addItem(magicHours);
        navigationMenu.addItem(rewards);
        navigationMenu.addItem(influencers);
        navigationMenu.addItem(shows);
        navigationMenu.setOnTabSelectedListener((position, wasSelected) -> {
            NavigationPresenter navigationPresenter = getNavigationPresenter();
            Screen screen = navigationPresenter.getScreen(position);
            navigationPresenter.goTo(screen);
            return true;
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getNavigationMenu().setCurrentItem(getTabPosition());
    }

    protected abstract int getTabPosition();

    public abstract NavigationPresenter getNavigationPresenter();

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Nullable
    @Override
    public Navigator getNavigator() {
        return new TabNavigator(this, R.id.container);
    }
}
