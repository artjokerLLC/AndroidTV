package com.smb.navigation;

import com.smb.di.DependencyContainer;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;

/**
 * Created by dev on 19.02.18.
 */

public class NavigationLifecycle implements AndroidNavigationLifecycle {
    private static NavigationLifecycle navigationLifecycle;

    @Inject
    NavigatorHolder navigatorHolder;

    public NavigationLifecycle() {
        DependencyContainer.getAppComponent().inject(this);
    }

    public static NavigationLifecycle getInstance() {
        if (navigationLifecycle == null) {
            navigationLifecycle = new NavigationLifecycle();
        }
        return navigationLifecycle;
    }

    @Override
    public void onPause() {
        navigatorHolder.removeNavigator();
    }

    @Override
    public void onResume(Navigator navigator) {
        navigatorHolder.setNavigator(navigator);
    }
}
