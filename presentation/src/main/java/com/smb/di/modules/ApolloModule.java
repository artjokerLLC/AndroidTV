package com.smb.di.modules;

import com.apollographql.apollo.ApolloClient;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.data.BuildConfig.GRAPH_AUTH_DATA_HOST;
import static com.data.BuildConfig.GRAPH_QL_DATA_HOST;
import static com.smb.data.http.graphql.GraphqlClientTypes.AUTHENTICATION;
import static com.smb.data.http.graphql.GraphqlClientTypes.DATA;

/**
 * Created by dev on 12.10.17.
 */
@Module
public class ApolloModule {

    @Singleton
    @Provides
    public Map<GraphqlClientTypes, ApolloClient> provideApollo() {

        HashMap<GraphqlClientTypes, ApolloClient> retrofitApiHashMap = new HashMap<>();
        retrofitApiHashMap.put(AUTHENTICATION, new ApolloBuilder(GRAPH_AUTH_DATA_HOST).create());
        retrofitApiHashMap.put(DATA, new ApolloBuilder(GRAPH_QL_DATA_HOST).create());

        return retrofitApiHashMap;
    }
}
