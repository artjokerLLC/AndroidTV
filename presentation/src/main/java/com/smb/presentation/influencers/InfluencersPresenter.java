package com.smb.presentation.influencers;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.smb.core.interactors.InfluencersDataUseCase;
import com.smb.core.models.holders.InfluencersDataHolder;
import com.smb.di.DependencyContainer;
import com.smb.presentation.DefaultSize;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

@InjectViewState
public class InfluencersPresenter extends MvpPresenter<InfluencersView> {

    @Inject
    Context appContext;
    @Inject
    InfluencersDataUseCase influencersDataUseCase;

    public InfluencersPresenter() {
        super();
        DependencyContainer.getAppComponent().inject(this);
    }

    public void getData() {
        influencersDataUseCase.execute(new DefaultSize(appContext), new DisposableSingleObserver<InfluencersDataHolder>() {
            @Override
            public void onSuccess(InfluencersDataHolder influencersDataHolder) {
                getViewState().onDataFetched(influencersDataHolder);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
