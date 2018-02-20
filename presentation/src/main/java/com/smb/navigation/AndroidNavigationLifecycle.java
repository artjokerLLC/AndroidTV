package com.smb.navigation;

import ru.terrakok.cicerone.Navigator;

/**
 * Created by dev on 19.02.18.
 */

public interface AndroidNavigationLifecycle {
    void onPause();

    void onResume(Navigator navigator);
}
