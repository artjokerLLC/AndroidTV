package com.smb.data.repositories.prizes;

import android.content.Context;

import com.smb.core.models.Prize;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.LocalUserRepository;
import com.smb.core.repositories.PrizesRepository;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.mappers.instances.AlmostYoursPrizesMapper;
import com.smb.data.mappers.instances.TopPrizesMapper;
import com.smb.data.repositories.base.BaseDataRepository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import data.AlmostYoursQuery;
import data.TopPrizesQuery;
import io.reactivex.Observable;

/**
 * Created by dev on 20.02.18.
 */

public class GraphPrizesRepository extends BaseDataRepository implements PrizesRepository {

    @Inject
    public GraphPrizesRepository(Context appContext, Map<GraphqlClientTypes, ApolloBuilder> apollo, LocalUserRepository localUserRepository) {
        super(appContext, apollo, localUserRepository);
    }

    @Override
    public Observable<List<Prize>> getTopPrizes(Size size) {
        updateToken();
        TopPrizesQuery topPrizesQuery = TopPrizesQuery.builder()
                .build();

        return query(topPrizesQuery)
                .map(response -> TopPrizesMapper.Companion.map(response.data()))
                .flatMap(prizes -> preloadToCache(getAppContext(), prizes, size), (prizes, files) -> prizes);
    }

    @Override
    public Observable<List<Prize>> getAlmostYours(Size size) {
        updateToken();
        AlmostYoursQuery query = AlmostYoursQuery.builder()
                .limit(10L)
                .build();
        return query(query)
                .map(response -> AlmostYoursPrizesMapper.Companion.map(response.data()))
                .flatMap(prizes -> preloadToCache(getAppContext(), prizes, size), (prizes, files) -> prizes);
    }
}
