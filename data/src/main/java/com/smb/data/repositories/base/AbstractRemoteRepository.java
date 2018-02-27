package com.smb.data.repositories.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.bumptech.glide.Glide;
import com.smb.core.models.base.Represantable;
import com.smb.core.models.util.Size;
import com.smb.data.RxTransformers;
import com.smb.data.errors.DefaultErrorHandler;
import com.smb.data.errors.RxErrorHandlers;
import com.smb.data.http.graphql.ApolloBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import io.reactivex.Observable;
import okhttp3.Interceptor;

/**
 * Created by dev on 31.01.18.
 */

public abstract class AbstractRemoteRepository {
    private ApolloBuilder apollo;
    private String token;

    public AbstractRemoteRepository(ApolloBuilder client) {
        apollo = client;
    }

    protected void setToken(String token) {
        this.token = token;
    }

    @NonNull
    private Interceptor token() {
        return chain -> chain.proceed(chain.request().newBuilder().header("Authorization", "Bearer " + token).build());
    }

    protected <D extends Mutation.Data, T, V extends Mutation.Variables> Observable<Response<T>> mutation(@Nonnull Mutation<D, T, V> mutation) {
        return Rx2Apollo.from(apollo.create(token()).mutate(mutation))
                .compose(RxTransformers.applySchedulers())
                .compose(RxErrorHandlers.applyHttpErrors(new DefaultErrorHandler()));
    }

    protected <D extends Mutation.Data, T, V extends Mutation.Variables> Observable<Response<T>> mutation(@Nonnull ApolloClient apollo,
                                                                                                          @Nonnull Mutation<D, T, V> mutation) {
        return Rx2Apollo.from(apollo.mutate(mutation))
                .compose(RxTransformers.applySchedulers())
                .compose(RxErrorHandlers.applyHttpErrors(new DefaultErrorHandler()));
    }

    protected <D extends Query.Data, T, V extends Query.Variables> Observable<Response<T>> query(@Nonnull Query<D, T, V> query) {
        return Rx2Apollo.from(apollo.create(token()).query(query))
                .compose(RxTransformers.applySchedulers())
                .compose(RxErrorHandlers.applyHttpErrors(new DefaultErrorHandler()));
    }

    protected <D extends Query.Data, T, V extends Query.Variables> Observable<Response<T>> query(@Nonnull ApolloClient apollo,
                                                                                                 @Nonnull Query<D, T, V> query) {
        return Rx2Apollo.from(apollo.query(query))
                .compose(RxTransformers.applySchedulers())
                .compose(RxErrorHandlers.applyHttpErrors(new DefaultErrorHandler()));
    }

    protected Observable<List<File>> preloadToCache(Context appContext, List<? extends Represantable> images, Size size) throws InterruptedException, java.util.concurrent.ExecutionException {
        if (images.size() > 0) {
            return Observable.fromIterable(images)
                    .map(image -> download(appContext, size, image.getIcon()))
                    .toList()
                    .toObservable()
                    .compose(RxTransformers.applySchedulers());
        } else return Observable.just(new ArrayList<>());
    }

    private File download(Context appContext, Size size, String image) throws InterruptedException, java.util.concurrent.ExecutionException {
        Log.e("Glide", "download: " + image);
        return Glide.with(appContext)
                .load(image)
                .downloadOnly(size.getWidth(), size.getHeight())
                .get();
    }
}
