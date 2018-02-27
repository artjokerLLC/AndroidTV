package com.smb.core.repositories;

import com.smb.core.models.Banner;
import com.smb.core.models.util.Size;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by dev on 20.02.18.
 */

public interface MagicHoursRepository {
    Observable<List<Banner>> getBanners(Size size);
}
