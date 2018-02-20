package com.smb.di;

import android.app.Application;

import com.smb.data.di.modules.ApolloModule;
import com.smb.data.di.modules.ContextModule;
import com.smb.di.components.AppComponent;
import com.smb.di.components.DaggerAppComponent;
import com.smb.di.modules.CiceroneModule;

/**
 * Created by dev on 24.01.18.
 */

public class DependencyContainer {

    private static AppComponent appComponent;


    public static void inject(Application application) {

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(application))
                .apolloModule(new ApolloModule())
                .ciceroneModule(new CiceroneModule())
                .build();

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
