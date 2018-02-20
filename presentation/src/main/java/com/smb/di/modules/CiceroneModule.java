package com.smb.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by dev on 19.02.18.
 */
@Module
public class CiceroneModule {
    private Cicerone<Router> cicerone;

    public CiceroneModule() {
        cicerone = Cicerone.create();
    }

    @Singleton
    @Provides
    public Router provideRouter() {
        return cicerone.getRouter();
    }

    @Singleton
    @Provides
    public NavigatorHolder provideNavigationHolder() {
        return cicerone.getNavigatorHolder();
    }

    @Singleton
    @Provides
    public Cicerone<Router> provideCicerone() {
        return cicerone;
    }
}
