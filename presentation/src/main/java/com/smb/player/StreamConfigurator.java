package com.smb.player;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import javax.inject.Inject;

/**
 * Created by dev on 14.02.18.
 */

public class StreamConfigurator {

    public static final String APPLICATION_NAME = "Social media broadcasting";
    private Context context;

    @Inject
    public StreamConfigurator(Context context) {
        this.context = context;
    }

    @NonNull
    public MediaSource getHlsMediaSource(String hls) {
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        String yourApplicationName = Util.getUserAgent(context, APPLICATION_NAME);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, yourApplicationName, bandwidthMeter);
        HlsMediaSource.Factory factory = new HlsMediaSource.Factory(dataSourceFactory);
        return factory.createMediaSource(Uri.parse(hls));
    }

    @NonNull
    public SimpleExoPlayer createExoPlayer() {
        return ExoPlayerFactory.newSimpleInstance(context, new DefaultTrackSelector());
    }
}
