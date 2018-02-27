package com.smb.core.repositories;

import io.reactivex.Observable;

/**
 * @param <T> - Type of returned entity
 * @param <P> - Type of data parameter for entity
 */
public interface EntityRepository <T, P> {
    Observable<T> getEntity(P params);
}
