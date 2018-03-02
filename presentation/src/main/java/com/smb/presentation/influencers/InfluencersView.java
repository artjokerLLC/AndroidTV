package com.smb.presentation.influencers;

import com.arellomobile.mvp.MvpView;
import com.smb.core.models.holders.InfluencersDataHolder;

public interface InfluencersView extends MvpView {

    void onDataFetched(InfluencersDataHolder influencersDataHolder);
}
