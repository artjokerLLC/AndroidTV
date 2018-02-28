package com.smb.presentation.home.activity;

import com.arellomobile.mvp.InjectViewState;
import com.smb.di.DependencyContainer;
import com.smb.navigation.Screen;
import com.smb.presentation.main.NavigationPresenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class HomeRootPresenter extends NavigationPresenter<HomeRootView> {
    @Inject
    Router router;

    public HomeRootPresenter() {
        super();
        DependencyContainer.getAppComponent().inject(this);
    }

    @Override
    public void goTo(Screen screen) {
        router.navigateTo(screen.getTag());
    }

    @Override
    public void root(Screen screen) {
        router.newRootScreen(screen.getTag());
    }

}
