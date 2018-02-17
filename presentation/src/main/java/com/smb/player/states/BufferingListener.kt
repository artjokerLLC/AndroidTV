package com.smb.player.states

import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.smb.data.RxBus

import io.reactivex.Observable

/**
 * Created by dev on 13.02.18.
 */

class BufferingListener : Player.EventListener {

    val bufferingState: Observable<BufferingState>
        get() = bufferingStateRxBus.toObservable()

    var bufferingStateRxBus = RxBus<BufferingState>()

    override fun onTimelineChanged(timeline: Timeline, manifest: Any) {

    }

    override fun onTracksChanged(trackGroups: TrackGroupArray, trackSelections: TrackSelectionArray) {

    }

    override fun onLoadingChanged(isLoading: Boolean) {

    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        bufferingStateRxBus.send(if (playbackState == Player.STATE_BUFFERING) BufferingState.START else BufferingState.STOP)
    }

    override fun onRepeatModeChanged(repeatMode: Int) {

    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

    }

    override fun onPlayerError(error: ExoPlaybackException) {

    }

    override fun onPositionDiscontinuity(reason: Int) {

    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {

    }

    override fun onSeekProcessed() {

    }
}
