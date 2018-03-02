package com.smb.core.interactors;

import com.smb.core.SingleUseCase;
import com.smb.core.models.holders.InfluencersDataHolder;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.InfluencersRepository;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by dev on 02.03.18.
 */

public class InfluencersDataUseCase extends SingleUseCase.ParametrizedUseCase<Size, InfluencersDataHolder> {

    private final InfluencersRepository influencersRepository;
    private final Map<SchedulerType, Scheduler> schedulers;

    @Inject
    public InfluencersDataUseCase(InfluencersRepository influencersRepository, Map<SchedulerType, Scheduler> schedulers) {
        super();
        this.influencersRepository = influencersRepository;
        this.schedulers = schedulers;
    }

    @NotNull
    @Override
    protected Single<InfluencersDataHolder> buildSingle(Size size) {
        return Single.fromObservable(
                Observable.just(new InfluencersDataHolder())
                        .zipWith(influencersRepository.getRecommendedInfluencers(size), InfluencersDataHolder::setRecommended)
                        .zipWith(influencersRepository.getFollowed(size), InfluencersDataHolder::setFollowed)
                        .zipWith(influencersRepository.getCreators(size), InfluencersDataHolder::setCreators)
                        .zipWith(influencersRepository.getCategorizedInfluencers(size), InfluencersDataHolder::setCategorizedInfluences)

        );
    }

    @NotNull
    @Override
    public Map<SchedulerType, Scheduler> getSchedulers() {
        return schedulers;
    }
}
