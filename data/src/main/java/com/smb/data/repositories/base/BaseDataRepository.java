package com.smb.data.repositories.base;

import android.content.Context;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.smb.core.repositories.LocalUserRepository;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;

import java.util.Map;

import javax.annotation.Nonnull;

import io.reactivex.Observable;

import static com.smb.data.http.graphql.GraphqlClientTypes.DATA;

/**
 * Created by dev on 26.02.18.
 */

public abstract class BaseDataRepository extends AbstractRemoteRepository {


    private final Context appContext;
    private final LocalUserRepository localUserRepository;
    private volatile boolean isTokenUpdated = false;

    public BaseDataRepository(Context appContext, Map<GraphqlClientTypes, ApolloBuilder> apollo, LocalUserRepository localUserRepository) {
        super(apollo.get(DATA));
        this.appContext = appContext;
        this.localUserRepository = localUserRepository;
    }

    public Context getAppContext() {
        return appContext;
    }

    public void updateToken() {
        setToken(localUserRepository.get().getToken());
    }

    @Override
    protected synchronized void setToken(String token) {
        super.setToken(token);
        isTokenUpdated = true;
    }

    @Override
    protected <D extends Mutation.Data, T, V extends Mutation.Variables> Observable<Response<T>> mutation(@Nonnull Mutation<D, T, V> mutation) {
        Observable<Response<T>> action = super.mutation(mutation);
        checkToken();
        return action;
    }

    @Override
    protected <D extends Mutation.Data, T, V extends Mutation.Variables> Observable<Response<T>> mutation(@Nonnull ApolloClient apollo, @Nonnull Mutation<D, T, V> mutation) {
        Observable<Response<T>> action = super.mutation(apollo, mutation);
        checkToken();
        return action;
    }

    @Override
    protected <D extends Query.Data, T, V extends Query.Variables> Observable<Response<T>> query(@Nonnull Query<D, T, V> query) {
        Observable<Response<T>> action = super.query(query);
        checkToken();
        return action;
    }

    private synchronized void checkToken() {
        if (isTokenUpdated) {
            isTokenUpdated = false;
        } else {
            throw new IllegalStateException("Did you forget to update token before request ?");
        }
    }

    @Override
    protected <D extends Query.Data, T, V extends Query.Variables> Observable<Response<T>> query(@Nonnull ApolloClient apollo, @Nonnull Query<D, T, V> query) {
        Observable<Response<T>> action = super.query(apollo, query);
        checkToken();
        return action;
    }
}
