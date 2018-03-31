package com.tv.di;

import android.app.Application;

import com.tv.di.components.AppComponent;
import com.tv.di.components.DaggerAppComponent;
import com.tv.di.modules.ContextModule;

/**
 * Created by dev on 24.01.18.
 */

public class DependencyContainer {

    private static AppComponent appComponent;


    public static void inject(Application application) {

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(application))
                .build();

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
