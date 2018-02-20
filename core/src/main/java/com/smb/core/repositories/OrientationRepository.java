package com.smb.core.repositories;

import io.reactivex.Observable;

/**
 * Created by dev on 17.02.18.
 */

public interface OrientationRepository {

    Observable<Orientation> get();

    void start();

    void stop();

    enum Orientation {
        PORTRAIT, LANDSCAPE
    }
}
