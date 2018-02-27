package com.smb.presentation.home;

import com.arellomobile.mvp.MvpView;
import com.smb.core.models.holders.HomeScreenDataHolder;

public interface HomeView extends MvpView {

    void onDataFetched(HomeScreenDataHolder homeScreenDataHolder);
}
