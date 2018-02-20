package com.smb.data.repositories.tv;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.repositories.AbstractRemoteRepository;

import java.util.Map;

import javax.inject.Inject;

import data.ShowQuery;
import io.reactivex.Observable;

import static com.smb.data.http.graphql.GraphqlClientTypes.TEST;

public class ShowRepository extends AbstractRemoteRepository {

    @Inject
    public ShowRepository(Map<GraphqlClientTypes, ApolloClient> apollo) {
        super(apollo.get(TEST));
    }

    public Observable<Response<ShowQuery.Data>> getShows(String showId) {
        ShowQuery showsQuery = ShowQuery.builder()
                .showId(showId)
                .build();
        return query(showsQuery);
    }
}
