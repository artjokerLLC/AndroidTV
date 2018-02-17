package com.smb.presentation.player;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.smb.R;
import com.smb.base.BaseActivity;
import com.smb.data.repositories.api.OrientationRepository;
import com.smb.player.SmbExoPlayer;

public class PlayerActivity extends BaseActivity implements PlayerView {
    public static final String TAG = "PlayerActivity";
    public static final String HLS = "hls";

    @InjectPresenter
    PlayerPresenter mPlayerPresenter;

    private SimpleExoPlayerView simpleExoPlayerView;
    private ImageView fullScreenIcon;
    private SmbExoPlayer smbExoPlayer;


    public static Intent getIntent(final Context context, String link) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(HLS, link);
        return intent;
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_player;
    }

    @Override
    protected void onPresenterReady() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        String hls = getIntent().getStringExtra(HLS);
        simpleExoPlayerView = findViewById(R.id.player);
        smbExoPlayer = mPlayerPresenter.getPlayer();
        smbExoPlayer.setupOnView(simpleExoPlayerView);
        smbExoPlayer.setBufferingBar(findViewById(R.id.buffering_bar));
        smbExoPlayer.setTimeDuration(findViewById(R.id.time_duration));
        smbExoPlayer.setTimePosition(findViewById(R.id.time_position));
        smbExoPlayer.start(hls);
        fullScreenIcon = findViewById(R.id.exo_fullscreen_mode);

        fullScreenIcon.setOnClickListener(v -> {
            if (smbExoPlayer.isFullScreenMode()) {
                closeFullScreen(smbExoPlayer);
            } else {
                openFullScreen(smbExoPlayer);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        mPlayerPresenter.startOrientationTracking();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPlayerPresenter.stopOrientationTracking();

    }

    private void closeFullScreen(SmbExoPlayer smbExoPlayer) {
        fullScreenIcon.setImageResource(R.drawable.open_full_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        smbExoPlayer.closeFullScreen();
    }

    private void openFullScreen(SmbExoPlayer smbExoPlayer) {
        fullScreenIcon.setImageResource(R.drawable.close_full_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        smbExoPlayer.openFullScreen();
    }


    @Override
    public void changeOrientationTo(OrientationRepository.Orientation orientation) {
        boolean fullScreenMode = smbExoPlayer.isFullScreenMode();
        switch (orientation) {
            case PORTRAIT:
                if (fullScreenMode) {
                    closeFullScreen(smbExoPlayer);
                }
                break;
            case LANDSCAPE:
                if (!fullScreenMode) {
                    openFullScreen(smbExoPlayer);
                }
                break;
        }
    }
}
