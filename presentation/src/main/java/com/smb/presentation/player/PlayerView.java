package com.smb.presentation.player;

import com.arellomobile.mvp.MvpView;
import com.smb.core.repositories.OrientationRepository;

public interface PlayerView extends MvpView {

    void changeOrientationTo(OrientationRepository.Orientation orientation);
}
