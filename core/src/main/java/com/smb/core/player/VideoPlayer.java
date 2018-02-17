package com.smb.core.player;

/**
 * Created by dev on 09.02.18.
 */

public interface VideoPlayer {
    void start(String hls);

    void onDestroy();

    void closeFullScreen();

    void openFullScreen();
}
