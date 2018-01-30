package com.smb.data.repositories;


import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.models.SocialLoginResult;

import java.util.Map;

import javax.inject.Inject;

import data.VideosQuery;
import guest.RegisterMutation;
import io.reactivex.Observable;

import static com.smb.data.http.graphql.GraphqlClientTypes.AUTHENTICATION;

/**
 * Created by dev on 24.01.18.
 */

public class TokenRepository {

    private ApolloClient apollo;

    @Inject
    public TokenRepository(Map<GraphqlClientTypes, ApolloClient> apollo) {
        this.apollo = apollo.get(AUTHENTICATION);
    }

    public Observable authorize(SocialLoginResult data) {
        RegisterMutation mutation = RegisterMutation.builder().build();
        VideosQuery videosQuery = VideosQuery.builder()
                .build();

        return Rx2Apollo.from(apollo.mutate(mutation));

    }
}
