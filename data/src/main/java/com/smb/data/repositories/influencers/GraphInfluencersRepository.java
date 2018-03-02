package com.smb.data.repositories.influencers;

import android.content.Context;

import com.smb.core.models.CategorizedInfluence;
import com.smb.core.models.TopInfluencer;
import com.smb.core.models.complex.Influencer;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.InfluencersRepository;
import com.smb.core.repositories.LocalUserRepository;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.mappers.instances.InfluencerMapper;
import com.smb.data.mappers.instances.TopInfluencersMapper;
import com.smb.data.repositories.base.BaseDataRepository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import data.FollowedInfluencerQuery;
import data.InfluenceByTypeQuery;
import data.InfluencersCategoriesQuery;
import data.RecommendedInfluencersQuery;
import data.TopInfluencersQuery;
import io.reactivex.Observable;

/**
 * Created by dev on 20.02.18.
 */

public class GraphInfluencersRepository extends BaseDataRepository implements InfluencersRepository {


    @Inject
    public GraphInfluencersRepository(Context appContext, Map<GraphqlClientTypes, ApolloBuilder> apollo, LocalUserRepository localUserRepository) {
        super(appContext, apollo, localUserRepository);
    }

    @Override
    public Observable<List<TopInfluencer>> getTopInfluencers(Size size) {
        updateToken();
        TopInfluencersQuery query = TopInfluencersQuery.builder()
                .build();
        return query(query)
                .map(response -> TopInfluencersMapper.Companion.map(response.data()))
                .flatMap(topInfluencers -> preloadToCache(getAppContext(), topInfluencers, size), (topInfluencers, files) -> topInfluencers);
    }

    @Override
    public Observable<List<Influencer>> getFollowed(Size size) {
        updateToken();
        FollowedInfluencerQuery followedInfluencerQuery = FollowedInfluencerQuery.builder()
                .build();
        return query(followedInfluencerQuery)
                .map(response -> InfluencerMapper.Companion.map(response.data()))
                .flatMap(followed -> preloadToCache(getAppContext(), followed, size), (followed, files) -> followed);
    }

    @Override
    public Observable<List<Influencer>> getRecommendedInfluencers(Size size) {
        updateToken();
        RecommendedInfluencersQuery query = RecommendedInfluencersQuery.builder()
                .build();
        return query(query)
                .map(response -> InfluencerMapper.Companion.mapRecommended(response.data()))
                .flatMap(recommended -> preloadToCache(getAppContext(), recommended, size), (recommended, files) -> recommended);

    }

    @Override
    public Observable<List<Influencer>> getCreators(Size size) {
        updateToken();
        InfluenceByTypeQuery query = InfluenceByTypeQuery.builder()
                .type(2L)
                .build();
        return query(query)
                .map(response -> InfluencerMapper.Companion.mapCreators(response.data()))
                .flatMap(creators -> preloadToCache(getAppContext(), creators, size), (creators, files) -> creators);
    }

    @Override
    public Observable<List<CategorizedInfluence>> getCategorizedInfluencers(Size size) {
        updateToken();
        InfluencersCategoriesQuery query = InfluencersCategoriesQuery.builder().build();
        return query(query)
                .map(response -> InfluencerMapper.Companion.mapCategories(response.data()))
                .flatMap(Observable::fromIterable)
                .flatMap(category -> preloadToCache(getAppContext(), category.getInfluencers(), size), (category, files) -> category)
                .toList()
                .toObservable();
    }
}
