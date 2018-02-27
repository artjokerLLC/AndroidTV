package com.smb.data.repositories.mhours;

import android.content.Context;

import com.smb.core.models.Banner;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.LocalUserRepository;
import com.smb.core.repositories.MagicHoursRepository;
import com.smb.data.RxTransformers;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.mappers.instances.BannerMapper;
import com.smb.data.repositories.base.BaseDataRepository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import data.BannersQuery;
import io.reactivex.Observable;

import static data.BannersQuery.builder;

/**
 * Created by dev on 20.02.18.
 */

public class GraphMagicHoursRepository extends BaseDataRepository implements MagicHoursRepository {

    @Inject
    public GraphMagicHoursRepository(Context appContext,
                                     Map<GraphqlClientTypes, ApolloBuilder> apollo,
                                     LocalUserRepository localUserRepository) {
        super(appContext, apollo, localUserRepository);

    }

    @Override
    public Observable<List<Banner>> getBanners(Size size) {
        BannersQuery bannersQuery = builder()
                .build();
        updateToken();
        return query(bannersQuery)
                .compose(RxTransformers.applySchedulers())
                .map(dataResponse -> BannerMapper.Companion.map(dataResponse.data()))
                .flatMap(banners -> preloadToCache(getAppContext(), banners, size), (banners, files) -> banners);
    }


}
