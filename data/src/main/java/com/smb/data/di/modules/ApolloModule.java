package com.smb.data.di.modules;

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
    public Map<GraphqlClientTypes, ApolloBuilder> provideApollo() {

        HashMap<GraphqlClientTypes, ApolloBuilder> retrofitApiHashMap = new HashMap<>();
        retrofitApiHashMap.put(AUTHENTICATION, new ApolloBuilder(GRAPH_AUTH_DATA_HOST));
        retrofitApiHashMap.put(DATA, new ApolloBuilder(GRAPH_QL_DATA_HOST));
        return retrofitApiHashMap;
    }


}
