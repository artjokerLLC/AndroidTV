package com.smb.core.interactors;

import com.smb.core.BaseUseCase;
import com.smb.core.SingleUseCase;
import com.smb.core.models.holders.ShowsDataHolder;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.ShowRepository;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by dev on 01.03.18.
 */

public class ShowsDataUseCase extends SingleUseCase.ParametrizedUseCase<Size, ShowsDataHolder> {

    private final ShowRepository showRepository;
    private final Map<BaseUseCase.SchedulerType, Scheduler> schedulers;

    @Inject
    public ShowsDataUseCase(ShowRepository showRepository, Map<BaseUseCase.SchedulerType, Scheduler> schedulers) {
        super();
        this.showRepository = showRepository;
        this.schedulers = schedulers;
    }

    @NotNull
    @Override
    protected Single<ShowsDataHolder> buildSingle(Size size) {
        return Single.fromObservable(
                Observable.just(new ShowsDataHolder())
                        .zipWith(showRepository.getRecommendedShows(size), ShowsDataHolder::setRecommended)
                        .zipWith(showRepository.getSubscription(size), ShowsDataHolder::setSubscriptions)
                        .zipWith(showRepository.getCategorizedShows(size), ShowsDataHolder::setCategories)

        );
    }

    @NotNull
    @Override
    public Map<SchedulerType, Scheduler> getSchedulers() {
        return schedulers;
    }
}
