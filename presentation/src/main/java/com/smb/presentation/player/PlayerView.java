package com.smb.presentation.player;

import com.arellomobile.mvp.MvpView;
import com.smb.data.repositories.api.OrientationRepository;

public interface PlayerView extends MvpView {

    void changeOrientationTo(OrientationRepository.Orientation orientation);
}
