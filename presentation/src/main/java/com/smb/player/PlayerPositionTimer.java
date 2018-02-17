package com.smb.player;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.smb.data.RxBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dev on 13.02.18.
 */

class PlayerPositionTimer {
    private RxBus<Long> rxBus = new RxBus<>();

    public PlayerPositionTimer(SimpleExoPlayer simpleExoPlayer) {
        Observable.interval(1, TimeUnit.SECONDS)
                .map(delay -> simpleExoPlayer.getCurrentPosition())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> rxBus.send(aLong));
    }

    public Observable<Long> getTimePosition() {
        return rxBus.toObservable();
    }
}
