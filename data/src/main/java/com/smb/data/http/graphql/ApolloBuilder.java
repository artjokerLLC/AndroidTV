package com.smb.data.http.graphql;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * Created by dev on 24.01.18.
 */

public class ApolloBuilder {

    private String url;

    public ApolloBuilder(String url) {
        this.url = url;
    }

    public ApolloClient create() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okhttp3.logging.HttpLoggingInterceptor interceptor = new okhttp3.logging.HttpLoggingInterceptor();
        interceptor.setLevel(BODY);
        builder.addNetworkInterceptor(interceptor);
        builder.addInterceptor(interceptor);

        return ApolloClient.builder()
                .serverUrl(url)
                .okHttpClient(builder.build())
                .build();
    }
}
