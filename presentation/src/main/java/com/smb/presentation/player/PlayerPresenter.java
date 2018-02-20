package com.smb.presentation.player;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.smb.core.repositories.OrientationRepository;
import com.smb.data.repositories.shows.TestShowsRepository;
import com.smb.di.DependencyContainer;
import com.smb.player.SmbExoPlayer;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class PlayerPresenter extends MvpPresenter<PlayerView> {
    @Inject
    TestShowsRepository showsRepository;
    @Inject
    SmbExoPlayer smbExoPlayer;
    @Inject
    OrientationRepository orientationRepository;
    private Disposable orientationDisposable;

    @Override
    public void attachView(PlayerView view) {
        super.attachView(view);
        DependencyContainer.getAppComponent().inject(this);
    }

    @Override
    public void detachView(PlayerView view) {
        super.detachView(view);
        smbExoPlayer.onDestroy();
    }

    public SmbExoPlayer getPlayer() {
        return smbExoPlayer;
    }


    public void startOrientationTracking() {
        orientationRepository.start();
        orientationDisposable = orientationRepository.get()
                .subscribe(orientation -> getViewState().changeOrientationTo(orientation));
    }

    public void stopOrientationTracking() {
        orientationRepository.stop();
        if (orientationDisposable != null && !orientationDisposable.isDisposed()) {
            orientationDisposable.dispose();
        }
    }
}
