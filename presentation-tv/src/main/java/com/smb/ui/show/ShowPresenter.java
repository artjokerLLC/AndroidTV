package com.smb.ui.show;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.smb.data.RxTransformers;
import com.smb.data.mappers.ShowMapper;
import com.smb.data.repositories.tv.ShowRepository;
import com.smb.di.DependencyContainer;

import javax.inject.Inject;

@InjectViewState
public class ShowPresenter extends MvpPresenter<ShowView> {

    @Inject
    ShowRepository showRepository;

    public ShowPresenter() {
        DependencyContainer.getAppComponent().inject(this);
    }

    public void getShow(String showId) {
        showRepository.getShows(showId)
                .compose(RxTransformers.applySchedulers())
                .compose(RxTransformers.applyProgress(() -> getViewState().showProgress(), () -> getViewState().hideProgress()))
                .map(ShowMapper.Companion::map)
                .subscribe(show -> getViewState().showFragment(show), Throwable::printStackTrace);
    }
}
