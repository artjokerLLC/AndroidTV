package com.smb.di.components;

import android.content.Context;

import com.apollographql.apollo.ApolloClient;
import com.smb.MainFragment;
import com.smb.data.di.modules.ApolloModule;
import com.smb.data.di.modules.ContextModule;
import com.smb.data.http.graphql.GraphqlClientTypes;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dev on 24.01.18.
 */
@Singleton
@Component(modules = {ContextModule.class, ApolloModule.class})
public interface AppComponent {

    Context getContext();

    Map<GraphqlClientTypes, ApolloClient> getApollo();

    void inject(@NotNull MainFragment mainFragment);
}
