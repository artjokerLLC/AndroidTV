package com.tv.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }
}
