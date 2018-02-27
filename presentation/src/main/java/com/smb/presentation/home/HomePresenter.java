package com.smb.presentation.home;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.smb.R;
import com.smb.core.models.holders.HomeScreenDataHolder;
import com.smb.core.models.util.Size;
import com.smb.core.repositories.InfluencersRepository;
import com.smb.core.repositories.MagicHoursRepository;
import com.smb.core.repositories.PrizesRepository;
import com.smb.core.repositories.ShowRepository;
import com.smb.core.repositories.VideosRepository;
import com.smb.di.DependencyContainer;

import javax.inject.Inject;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {
    @Inject
    MagicHoursRepository magicHoursRepository;
    @Inject
    PrizesRepository prizesRepository;
    @Inject
    InfluencersRepository influencersRepository;
    @Inject
    ShowRepository showRepository;
    @Inject
    VideosRepository videosRepository;
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
        HomeScreenDataHolder homeScreenDataHolder = new HomeScreenDataHolder();

        Size defaultSize = new Size(topPrizeWidth, topPrizeHeight);
        Size bannerSize = new Size(bannerWidth, bannerHeight);

        magicHoursRepository.getBanners(bannerSize)
                .zipWith(prizesRepository.getTopPrizes(defaultSize), (banners, prizes) -> {
                    homeScreenDataHolder.setBanners(banners);
                    homeScreenDataHolder.setPrizes(prizes);
                    return homeScreenDataHolder;
                })
                .zipWith(influencersRepository.getTopInfluencers(defaultSize), (holder, topInfluencers) -> {
                    holder.setTopInfluencers(topInfluencers);
                    return holder;
                })
                .zipWith(showRepository.getTopShows(defaultSize), (holder, shows) -> {
                    holder.setTopShows(shows);
                    return holder;
                })
                .zipWith(showRepository.getNewReleases(defaultSize), (holder, releases) -> {
                    holder.setNewReleases(releases);
                    return holder;
                })
                .zipWith(showRepository.getSubscription(defaultSize), (holder, followedShows) -> {
                    holder.setFollowedShows(followedShows);
                    return holder;
                })
                .zipWith(influencersRepository.getFollowed(defaultSize), (holder, followedInfluencers) -> {
                    holder.setFollowedInfluencers(followedInfluencers);
                    return holder;
                })
                .zipWith(videosRepository.getUnwatchedVideos(defaultSize), (holder, videos) -> {
                    holder.setUnwatched(videos);
                    return holder;
                })
                .zipWith(prizesRepository.getAlmostYours(defaultSize), (holder, almost) -> {
                    holder.setAlmostYours(almost);
                    return homeScreenDataHolder;
                })
                .zipWith(showRepository.getCategorizedShows(defaultSize), (holder, shows) -> {
                    holder.setCategories(shows);
                    return homeScreenDataHolder;
                })
                .subscribe(data -> getViewState().onDataFetched(data));
    }

}
