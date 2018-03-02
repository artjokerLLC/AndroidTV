package com.smb.presentation.home;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.smb.R;
import com.smb.core.interactors.HomeDataUseCase;
import com.smb.core.models.HomePreviews;
import com.smb.core.models.Show;
import com.smb.core.models.holders.HomeScreenDataHolder;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.ShowRepository;
import com.smb.di.DependencyContainer;
import com.smb.presentation.DefaultSize;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {
    @Inject
    HomeDataUseCase homeDataUseCase;
    @Inject
    ShowRepository showRepository;
    @Inject
    Context appContext;

    public HomePresenter() {
        super();
        DependencyContainer.getAppComponent().inject(this);
    }

    public void getData() {
        int bannerWidth = (int) appContext.getResources().getDimension(R.dimen.banner_width);
        int bannerHeight = (int) appContext.getResources().getDimension(R.dimen.banner_height);

        Size defaultSize = new DefaultSize(appContext);
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

    public void loadTest() {
        showRepository.getShows(new DefaultSize(appContext)).subscribe(new Consumer<List<Show>>() {
            @Override
            public void accept(List<Show> shows) throws Exception {
                Log.e("---", "accept: succcess");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("---", "accept: error");
            }
        });
    }
}
