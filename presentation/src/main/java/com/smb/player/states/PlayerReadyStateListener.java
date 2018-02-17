package com.smb.player.states;

import com.google.android.exoplayer2.Player;
import com.smb.data.RxBus;

import io.reactivex.Observable;

/**
 * Created by dev on 13.02.18.
 */

public class PlayerReadyStateListener extends StateListener {

    private RxBus<PlayerState> rxBus = new RxBus<>();

    public Observable<PlayerState> getState() {
        return rxBus.toObservable();
    }

    @Override
    public void onPlayerStateChanged(int playbackState) {
        if (playbackState == Player.STATE_READY) {
            rxBus.send(PlayerState.READY);
        }
    }
}
