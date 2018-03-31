package com.tv.di.components;

import android.content.Context;

import com.tv.di.modules.ContextModule;
import com.tv.ui.show.ShowPresenter;
import com.tv.ui.shows.ShowsFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class})
public interface AppComponent {

    Context getContext();

    void inject(@NotNull ShowsFragment showsFragment);

    void inject(@NotNull ShowPresenter showPresenter);

}
