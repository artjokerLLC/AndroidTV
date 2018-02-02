package com.smb.data.repositories.tv;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.repositories.AbstractRemoteRepository;

import java.util.Map;

import javax.inject.Inject;

import data.ShowsQuery;
import io.reactivex.Observable;

import static com.smb.data.http.graphql.GraphqlClientTypes.TEST;

public class TestShowsRepository extends AbstractRemoteRepository {

    @Inject
    public TestShowsRepository(Map<GraphqlClientTypes, ApolloClient> apollo) {
        super(apollo.get(TEST));
    }

    public Observable<Response<ShowsQuery.Data>> getShows() {
        ShowsQuery showsQuery = ShowsQuery.builder()
                .build();
        return query(showsQuery);
    }
}
