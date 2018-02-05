package com.smb.data;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus<T> {

    private final PublishSubject<T> bus = PublishSubject.create();

    public void send(T o) {
        bus.onNext(o);
    }

    public void error(Throwable e) {
        bus.onError(e);
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }

    public Observable<T> toObservable() {
        return bus;
    }
}
