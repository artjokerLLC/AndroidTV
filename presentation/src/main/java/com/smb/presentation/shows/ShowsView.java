package com.smb.presentation.shows;

import com.arellomobile.mvp.MvpView;
import com.smb.core.models.holders.ShowsDataHolder;

public interface ShowsView extends MvpView {

    void onDataFetched(ShowsDataHolder data);
}
