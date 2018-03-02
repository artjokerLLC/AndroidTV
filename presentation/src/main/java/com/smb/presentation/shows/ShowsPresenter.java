package com.smb.presentation.shows;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.smb.core.interactors.ShowsDataUseCase;
import com.smb.core.models.holders.ShowsDataHolder;
import com.smb.core.models.util.Size;
import com.smb.di.DependencyContainer;
import com.smb.presentation.DefaultSize;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

@InjectViewState
public class ShowsPresenter extends MvpPresenter<ShowsView> {
    @Inject
    ShowsDataUseCase showsDataUseCase;
    @Inject
    Context appContext;

    public ShowsPresenter() {
        super();
        DependencyContainer.getAppComponent().inject(this);
    }

    public void getData() {
        Size size = new DefaultSize(appContext);
        showsDataUseCase.execute(size, new DisposableSingleObserver<ShowsDataHolder>() {
            @Override
            public void onSuccess(ShowsDataHolder showsDataHolder) {
                getViewState().onDataFetched(showsDataHolder);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
