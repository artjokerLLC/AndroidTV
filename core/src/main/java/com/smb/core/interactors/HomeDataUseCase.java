package com.smb.core.interactors;

import com.smb.core.BaseUseCase;
import com.smb.core.SingleUseCase;
import com.smb.core.models.HomePreviews;
import com.smb.core.models.holders.HomeScreenDataHolder;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.InfluencersRepository;
import com.smb.core.repositories.MagicHoursRepository;
import com.smb.core.repositories.PrizesRepository;
import com.smb.core.repositories.ShowRepository;
import com.smb.core.repositories.VideosRepository;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by dev on 27.02.18.
 */

public class HomeDataUseCase extends SingleUseCase.ParametrizedUseCase<HomePreviews, HomeScreenDataHolder> {
    private final MagicHoursRepository magicHoursRepository;
    private final PrizesRepository prizesRepository;
    private final InfluencersRepository influencersRepository;
    private final ShowRepository showRepository;
    private final VideosRepository videosRepository;
    private Map<BaseUseCase.SchedulerType, Scheduler> schedulers;

    @Inject
    public HomeDataUseCase(MagicHoursRepository magicHoursRepository,
                           PrizesRepository prizesRepository,
                           InfluencersRepository influencersRepository,
                           ShowRepository showRepository,
                           VideosRepository videosRepository,
                           Map<BaseUseCase.SchedulerType, Scheduler> schedulers) {
        super();
        this.magicHoursRepository = magicHoursRepository;
        this.prizesRepository = prizesRepository;
        this.influencersRepository = influencersRepository;
        this.showRepository = showRepository;
        this.videosRepository = videosRepository;
        this.schedulers = schedulers;
    }

    @NotNull
    @Override
    protected Single<HomeScreenDataHolder> buildSingle(HomePreviews params) {
        Size defaultSize = params.getDefaultSize();
        Size bannerSize = params.getBannerSize();
        return Single.fromObservable(
                Observable.just(new HomeScreenDataHolder())
                        .zipWith(magicHoursRepository.getBanners(bannerSize), HomeScreenDataHolder::setBanners)
                        .zipWith(prizesRepository.getTopPrizes(defaultSize), HomeScreenDataHolder::setPrizes)
                        .zipWith(influencersRepository.getTopInfluencers(defaultSize), HomeScreenDataHolder::setTopInfluencers)
                        .zipWith(showRepository.getTopShows(defaultSize), HomeScreenDataHolder::setTopShows)
                        .zipWith(showRepository.getNewReleases(defaultSize), HomeScreenDataHolder::setNewReleases)
                        .zipWith(showRepository.getSubscription(defaultSize), HomeScreenDataHolder::setFollowedShows)
                        .zipWith(influencersRepository.getFollowed(defaultSize), HomeScreenDataHolder::setFollowedInfluencers)
                        .zipWith(videosRepository.getUnwatchedVideos(defaultSize), HomeScreenDataHolder::setUnwatched)
                        .zipWith(prizesRepository.getAlmostYours(defaultSize), HomeScreenDataHolder::setAlmostYours)
                        .zipWith(showRepository.getCategorizedShows(defaultSize), HomeScreenDataHolder::setCategories)
        );
    }

    @NotNull
    @Override
    public Map<SchedulerType, Scheduler> getSchedulers() {
        return schedulers;
    }

}
