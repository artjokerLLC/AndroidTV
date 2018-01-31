package com.smb.data.repositories;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.smb.data.errors.DefaultErrorHandler;
import com.smb.data.errors.RxErrorHandlers;

import javax.annotation.Nonnull;

import io.reactivex.Observable;

/**
 * Created by dev on 31.01.18.
 */

public abstract class AbstractRemoteRepository {
    private ApolloClient apollo;

    public AbstractRemoteRepository(ApolloClient client) {
        apollo = client;
    }

    protected <D extends Mutation.Data, T, V extends Mutation.Variables> Observable<Response<T>> mutation(@Nonnull Mutation<D, T, V> mutation) {
        return Rx2Apollo.from(apollo.mutate(mutation))
                .compose(RxErrorHandlers.applyHttpErrors(new DefaultErrorHandler()));
    }

    protected <D extends Mutation.Data, T, V extends Mutation.Variables> Observable<Response<T>> mutation(@Nonnull ApolloClient apollo,
                                                                                                          @Nonnull Mutation<D, T, V> mutation) {
        return Rx2Apollo.from(apollo.mutate(mutation))
                .compose(RxErrorHandlers.applyHttpErrors(new DefaultErrorHandler()));
    }
}
