package com.smb.data.repositories.shows;

import android.content.Context;

import com.smb.core.models.CategorizedShow;
import com.smb.core.models.Show;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.LocalUserRepository;
import com.smb.core.repositories.ShowRepository;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.mappers.instances.CategorizedShowMapper;
import com.smb.data.mappers.instances.ShowMapper;
import com.smb.data.repositories.base.BaseDataRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import data.FollowedShowQuery;
import data.OrderedShowsQuery;
import data.ShowQuery;
import data.ShowsQuery;
import data.TopShowsQuery;
import io.reactivex.Observable;


public class GraphShowRepository extends BaseDataRepository implements ShowRepository {

    private Show show;

    @Inject
    public GraphShowRepository(Context appContext, Map<GraphqlClientTypes, ApolloBuilder> apollo, LocalUserRepository userRepository) {
        super(appContext, apollo, userRepository);
    }

    @Override
    public Observable<Show> getEntity(String showId) {
        return show != null ? Observable.just(show) : getShowFromGraphQl(showId);
    }

    public Observable<Show> getShowFromGraphQl(String showId) {
        updateToken();
        ShowQuery showsQuery = ShowQuery.builder()
                .showId(showId)
                .build();
        return query(showsQuery)
                .map(ShowMapper.Companion::map)
                .doOnNext(this::setShow);
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Override
    public Observable<List<Show>> getTopShows(Size size) {
        updateToken();
        TopShowsQuery query = TopShowsQuery.builder()
                .build();
        return query(query)
                .map(dataResponse -> ShowMapper.Companion.map(dataResponse.data().topShow()))
                .flatMap(topShows -> preloadToCache(getAppContext(), topShows, size), (topShows, files) -> topShows);

    }

    @Override
    public Observable<List<Show>> getNewReleases(Size size) {
        updateToken();
        OrderedShowsQuery orderedShowsQuery = OrderedShowsQuery.builder()
                .orderBy(Arrays.asList("created_at", "desc"))
                .limit(10L)
                .build();
        return query(orderedShowsQuery)
                .map(dataResponse -> ShowMapper.Companion.mapOrdered(dataResponse.data().shows()))
                .flatMap(releases -> preloadToCache(getAppContext(), releases, size), (releases, files) -> releases);

    }

    @Override
    public Observable<List<Show>> getSubscription(Size size) {
        updateToken();
        FollowedShowQuery followedShowQuery = FollowedShowQuery.builder()
                .build();
        return query(followedShowQuery)
                .map(dataResponse -> ShowMapper.Companion.mapFollowed(dataResponse.data().profile().subShow()))
                .flatMap(followings -> preloadToCache(getAppContext(), followings, size), (followings, files) -> followings);
    }

    @Override
    public Observable<List<Show>> getShows(Size size) {
        updateToken();
        ShowsQuery showsQuery = ShowsQuery.builder()
                .build();
        return query(showsQuery)
                .map(dataResponse -> ShowMapper.Companion.mapShows(dataResponse.data().shows()))
                .flatMap(shows -> preloadToCache(getAppContext(), shows, size), (shows, files) -> shows);
    }

    @Override
    public Observable<List<CategorizedShow>> getCategorizedShows(Size size) {
        return getShows(size).map(CategorizedShowMapper.INSTANCE::map);
    }


}
