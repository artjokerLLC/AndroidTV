package com.smb.presentation.home;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.smb.R;
import com.smb.core.interactors.HomeDataUseCase;
import com.smb.core.models.HomePreviews;
import com.smb.core.models.holders.HomeScreenDataHolder;
import com.smb.core.models.util.Size;
import com.smb.di.DependencyContainer;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {
    @Inject
    HomeDataUseCase homeDataUseCase;
    @Inject
    Context appContext;

    @Override
    public void attachView(HomeView view) {
        super.attachView(view);
        DependencyContainer.getAppComponent().inject(this);
    }

    public void getData() {
        int bannerWidth = (int) appContext.getResources().getDimension(R.dimen.banner_width);
        int bannerHeight = (int) appContext.getResources().getDimension(R.dimen.banner_height);
        int topPrizeWidth = (int) appContext.getResources().getDimension(R.dimen.imageTopPrizeWidth);
        int topPrizeHeight = (int) appContext.getResources().getDimension(R.dimen.imageTopPrizeHeight);
        Size defaultSize = new Size(topPrizeWidth, topPrizeHeight);
        Size bannerSize = new Size(bannerWidth, bannerHeight);
        HomePreviews previews = new HomePreviews(defaultSize, bannerSize);
        homeDataUseCase.execute(previews, new DisposableSingleObserver<HomeScreenDataHolder>() {

            @Override
            public void onSuccess(HomeScreenDataHolder data) {
                getViewState().onDataFetched(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

}
