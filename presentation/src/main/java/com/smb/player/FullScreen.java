package com.smb.player;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.smb.R;

/**
 * Created by dev on 14.02.18.
 */

public class FullScreen {
    private boolean isFullScreenMode = false;
    private Dialog dialog;
    private ViewGroup.LayoutParams layoutParams;
    private SimpleExoPlayerView exoPlayerView;
    private ViewGroup exoRootView;

    public FullScreen(SimpleExoPlayerView exoPlayerView) {
        this.exoPlayerView = exoPlayerView;
        exoRootView = ((ViewGroup) exoPlayerView.getParent());
    }

    public boolean isFullScreenMode() {
        return isFullScreenMode;
    }

    public void openFullScreen(Context activityContext) {
        isFullScreenMode = true;
        dialog = new Dialog(activityContext, R.style.ExoFullScreenTheme) {
            @Override
            public void onBackPressed() {
                if (isFullScreenMode) {
                    closeFullScreen();
                }
                super.onBackPressed();
            }
        };
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.windowAnimations = R.style.ExoFullScreenTheme;
        layoutParams = exoPlayerView.getLayoutParams();
        ((ViewGroup) exoPlayerView.getParent()).removeView(exoPlayerView);
        dialog.addContentView(exoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.show();
    }

    public void closeFullScreen() {
        ((ViewGroup) exoPlayerView.getParent()).removeView(exoPlayerView);
        exoPlayerView.setLayoutParams(layoutParams);
        exoRootView.addView(exoPlayerView);
        isFullScreenMode = false;
        dialog.dismiss();
    }
}
