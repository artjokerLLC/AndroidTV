package com.smb.data.repositories.influencers;

import android.content.Context;

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
}
