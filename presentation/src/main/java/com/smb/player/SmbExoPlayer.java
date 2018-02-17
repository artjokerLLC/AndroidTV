package com.smb.player;

import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.smb.core.player.VideoPlayer;
import com.smb.player.states.BufferingListener;
import com.smb.player.states.BufferingState;
import com.smb.player.states.PlayerReadyStateListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by dev on 12.02.18.
 */

public class SmbExoPlayer implements VideoPlayer {
    private final SimpleDateFormat format = new SimpleDateFormat("mm:ss", Locale.US);
    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer simpleExoPlayer;
    private StreamConfigurator streamConfigurator;
    private View bufferingBar;
    private TextView totalDuration;
    private TextView timePosition;
    private FullScreen fullScreenMode;

    @Inject
    public SmbExoPlayer(StreamConfigurator streamConfigurator) {
        this.streamConfigurator = streamConfigurator;
        simpleExoPlayer = streamConfigurator.createExoPlayer();
    }

    public void setupOnView(SimpleExoPlayerView exoPlayerView) {
        this.exoPlayerView = exoPlayerView;
        this.fullScreenMode = new FullScreen(exoPlayerView);
        exoPlayerView.setUseController(true);
        exoPlayerView.setPlayer(simpleExoPlayer);
        listeners();

    }

    private void listeners() {
        BufferingListener bufferingListener = new BufferingListener();
        simpleExoPlayer.addListener(bufferingListener);
        bufferingListener.getBufferingState()
                .filter(state -> bufferingBar != null)
                .subscribe(bufferingState -> bufferingBar.setVisibility(bufferingState.ordinal() == BufferingState.START.ordinal() ? View.VISIBLE : View.GONE));

        PlayerReadyStateListener playerReadyListener = new PlayerReadyStateListener();
        simpleExoPlayer.addListener(playerReadyListener);
        playerReadyListener.getState().subscribe(playerState -> onPlayerReady());

    }

    private void onPlayerReady() {
        if (totalDuration != null) {
            String time = format.format(new Date(simpleExoPlayer.getDuration()));
            totalDuration.setText(time);
        }
        setCurrentPosition(0);
        PlayerPositionTimer playerPositionTimer = new PlayerPositionTimer(simpleExoPlayer);
        playerPositionTimer.getTimePosition()
                .filter(along -> timePosition != null)
                .subscribe(this::setCurrentPosition);
    }

    private void setCurrentPosition(long aLong) {
        String time = format.format(new Date(aLong));
        timePosition.setText(time);
    }

    public void setBufferingBar(View bufferingBar) {
        this.bufferingBar = bufferingBar;
    }

    @Override
    public void start(String hls) {
        MediaSource mediaSource = streamConfigurator.getHlsMediaSource(hls);
        simpleExoPlayer.prepare(mediaSource);
    }

    @Override
    public void onDestroy() {
        exoPlayerView.getPlayer().release();
        exoPlayerView = null;
    }

    public void setTimeDuration(TextView timeDuration) {
        this.totalDuration = timeDuration;
    }

    public void setTimePosition(TextView timePosition) {
        this.timePosition = timePosition;
    }

    public boolean isFullScreenMode() {
        return fullScreenMode.isFullScreenMode();
    }

    @Override
    public void closeFullScreen() {
        fullScreenMode.closeFullScreen();
    }

    @Override
    public void openFullScreen() {
        fullScreenMode.openFullScreen(exoPlayerView.getContext());
    }
}
