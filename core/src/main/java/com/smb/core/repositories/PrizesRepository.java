package com.smb.core.repositories;

import com.smb.core.models.Prize;
import com.smb.core.models.util.Size;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by dev on 20.02.18.
 */

public interface PrizesRepository {
    Observable<List<Prize>> getTopPrizes(Size size);

    Observable<List<Prize>> getAlmostYours(Size size);
}
