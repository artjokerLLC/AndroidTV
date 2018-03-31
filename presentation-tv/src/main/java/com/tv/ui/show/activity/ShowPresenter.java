package com.tv.ui.show.activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ShowPresenter extends MvpPresenter<ShowView> {

//    @Inject
//    EntityRepository<Show, String> showRepository;
//
//
//    public ShowPresenter() {
//        DependencyContainer.getAppComponent().inject(this);
//    }

//    public void getShow(String showId) {
//        showRepository.getEntity(showId)
//                .compose(RxTransformers.applyProgress(() -> getViewState().showProgress(), () -> getViewState().hideProgress()))
//                .subscribe(show -> getViewState().showFragment(show), Throwable::printStackTrace);
//    }

    public void showFragments(String showId) {
        getViewState().showFragment(showId);
    }
}
