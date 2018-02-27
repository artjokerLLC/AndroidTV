package com.smb.core.repositories;

import com.smb.core.models.TopInfluencer;
import com.smb.core.models.complex.Influencer;
import com.smb.core.models.util.Size;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by dev on 20.02.18.
 */

public interface InfluencersRepository {
    Observable<List<TopInfluencer>> getTopInfluencers(Size size);

    Observable<List<Influencer>> getFollowed(Size size);
}
