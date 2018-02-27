package com.smb.data.repositories.videos;

import android.content.Context;

import com.smb.core.models.Video;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.LocalUserRepository;
import com.smb.core.repositories.VideosRepository;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.mappers.instances.VideoMapper;
import com.smb.data.repositories.base.BaseDataRepository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import data.UnwatchedVideosQuery;
import io.reactivex.Observable;

/**
 * Created by dev on 26.02.18.
 */

public class GraphVideosRepository extends BaseDataRepository implements VideosRepository {

    @Inject
    public GraphVideosRepository(Context appContext, Map<GraphqlClientTypes, ApolloBuilder> apollo, LocalUserRepository localUserRepository) {
        super(appContext, apollo, localUserRepository);
    }

    @Override
    public Observable<List<Video>> getUnwatchedVideos(Size size) {
        updateToken();
        UnwatchedVideosQuery unwatchedVideosQuery = UnwatchedVideosQuery.builder().build();
        return query(unwatchedVideosQuery)
                .map(response -> VideoMapper.Companion.map(response.data()))
                .flatMap(videos -> preloadToCache(getAppContext(), videos, size), (prizes, files) -> prizes);

    }
}
