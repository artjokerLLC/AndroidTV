package com.smb.di.components;

import android.content.Context;

import com.apollographql.apollo.ApolloClient;
import com.smb.data.di.modules.ApolloModule;
import com.smb.data.di.modules.ContextModule;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.di.modules.RepositoryModule;
import com.smb.presentation.home.HomePresenter;
import com.smb.presentation.main.MainPresenter;
import com.smb.presentation.player.PlayerPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dev on 24.01.18.
 */
@Singleton
@Component(modules = {ContextModule.class, ApolloModule.class, RepositoryModule.class})
public interface AppComponent {

    Context getContext();

    Map<GraphqlClientTypes, ApolloClient> getApollo();

    void inject(@NotNull MainPresenter mainPresenter);

    void inject(@NotNull HomePresenter homePresenter);

    void inject(@NotNull PlayerPresenter playerPresenter);
}
