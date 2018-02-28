package com.smb.presentation.main;

import android.support.design.widget.BottomNavigationView;

import com.smb.R;
import com.smb.base.BaseActivity;
import com.smb.navigation.Screen;
import com.smb.navigation.TabNavigator;

import org.jetbrains.annotations.Nullable;

import ru.terrakok.cicerone.Navigator;

public abstract class TabNavigationActivity extends BaseActivity {
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
    protected void onPresenterReady() {
        navigationMenu = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(navigationMenu);

        navigationMenu.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            NavigationPresenter navigationPresenter = getNavigationPresenter();
            Screen screen = navigationPresenter.getScreen(itemId);
            navigationPresenter.goTo(screen);
            return true;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getNavigationMenu().setSelectedItemId(getTabId());
    }

    protected abstract int getTabId();

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
