package com.smb.di.components;

import android.content.Context;

import com.smb.data.di.modules.ApolloModule;
import com.smb.data.di.modules.ContextModule;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.di.modules.RepositoryModule;
import com.smb.di.modules.RepositoryModule;
import com.smb.ui.show.ShowPresenter;
import com.smb.ui.shows.ShowsFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, ApolloModule.class, RepositoryModule.class})
public interface AppComponent {

    Context getContext();

    Map<GraphqlClientTypes, ApolloBuilder> getApollo();

    void inject(@NotNull ShowsFragment showsFragment);

    void inject(@NotNull ShowPresenter showPresenter);

}
