package com.smb.di.components;

import android.content.Context;

import com.apollographql.apollo.ApolloClient;
import com.smb.data.di.modules.ApolloModule;
import com.smb.data.di.modules.ContextModule;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.di.modules.CiceroneModule;
import com.smb.di.modules.RepositoryModule;
import com.smb.navigation.NavigationLifecycle;
import com.smb.presentation.auth.AuthPresenter;
import com.smb.presentation.home.activity.HomeRootPresenter;
import com.smb.presentation.player.PlayerPresenter;
import com.smb.presentation.test.TestPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Component;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by dev on 24.01.18.
 */
@Singleton
@Component(modules = {ContextModule.class, ApolloModule.class, RepositoryModule.class, CiceroneModule.class})
public interface AppComponent {

    Context getContext();

    Map<GraphqlClientTypes, ApolloClient> getApollo();

    Router getRouter();

    NavigatorHolder getNavigationHolder();

    void inject(@NotNull AuthPresenter authPresenter);

    void inject(@NotNull TestPresenter testPresenter);

    void inject(@NotNull PlayerPresenter playerPresenter);

    void inject(NavigationLifecycle navigationLifecycle);

    void inject(HomeRootPresenter navigationPresenter);
}
