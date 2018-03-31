package com.tv.ui.show;

import com.tv.RxTransformers;
import com.tv.di.DependencyContainer;
import com.tv.repositories.TestShowsRepository;

import javax.inject.Inject;

public class ShowPresenter {
    @Inject
    TestShowsRepository showsRepository;
    private ShowView showView;

    public ShowPresenter(ShowView showView) {
        this.showView = showView;
        DependencyContainer.getAppComponent().inject(this);
    }

    public void getShow(String showId) {
        showsRepository.getRandomShow()
                .compose(RxTransformers.applyProgress(() -> showView.showProgress(), () -> showView.hideProgress()))
                .subscribe(show -> showView.viewShow(show), Throwable::printStackTrace);
    }
}
