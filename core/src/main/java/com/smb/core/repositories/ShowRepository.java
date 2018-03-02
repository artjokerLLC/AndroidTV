package com.smb.core.repositories;

import com.smb.core.models.CategorizedShow;
import com.smb.core.models.Show;
import com.smb.core.models.util.Size;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by dev on 21.02.18.
 */

public interface ShowRepository extends EntityRepository<Show, String> {

    Observable<List<Show>> getTopShows(Size size);

    Observable<List<Show>> getNewReleases(Size size);

    Observable<List<Show>> getSubscription(Size size);

    Observable<List<Show>> getShows(Size size);

    Observable<List<CategorizedShow>> getCategorizedShows(Size size);

    Observable<List<Show>> getRecommendedShows(Size size);
}
