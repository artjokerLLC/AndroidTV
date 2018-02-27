package com.smb.ui.show;

import com.smb.core.repositories.ShowRepository;
import com.smb.data.RxTransformers;
import com.smb.di.DependencyContainer;

import javax.inject.Inject;

public class ShowPresenter{
    @Inject
    ShowRepository showRepository;
    private ShowView showView;

    public ShowPresenter(ShowView showView) {
        this.showView = showView;
        DependencyContainer.getAppComponent().inject(this);
    }

    public void getShow(String showId) {
        showRepository.getEntity(showId)
                .compose(RxTransformers.applyProgress(() -> showView.showProgress(), () -> showView.hideProgress()))
                .subscribe(show -> showView.viewShow(show), Throwable::printStackTrace);
    }
}
