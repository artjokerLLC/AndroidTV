package com.smb.data.http.graphql;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by dev on 24.01.18.
 */

public class ApolloBuilder {

    private String url;

    public ApolloBuilder(String url) {
        this.url = url;
    }

    public ApolloClient create() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);
        return ApolloClient.builder()
                .serverUrl(url)
                .okHttpClient(builder.build())
                .build();
    }
}
