package com.smb.core.models.holders;

import com.smb.core.models.Banner;
import com.smb.core.models.CategorizedShow;
import com.smb.core.models.Prize;
import com.smb.core.models.Show;
import com.smb.core.models.TopInfluencer;
import com.smb.core.models.Video;
import com.smb.core.models.complex.Influencer;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class HomeScreenDataHolder {

    private List<Show> topShows;
    private List<Banner> banners;
    private List<Prize> prizes;
    private List<Prize> almostYours;
    private List<Show> newReleases;
    private List<Show> followedShows;
    private List<CategorizedShow> categories;
    private List<TopInfluencer> topInfluencers;
    private List<Influencer> followedInfluencers;
    private List<Video> unwatched;


    public List<Banner> getBanners() {
        return banners;
    }

    public HomeScreenDataHolder setBanners(List<Banner> banners) {
        this.banners = banners;
        return this;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public HomeScreenDataHolder setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
        return this;
    }

    public List<TopInfluencer> getTopInfluencers() {
        return topInfluencers;
    }

    public HomeScreenDataHolder setTopInfluencers(List<TopInfluencer> topInfluencers) {
        this.topInfluencers = topInfluencers;
        return this;
    }

    public List<Show> getTopShows() {
        return topShows;
    }

    public HomeScreenDataHolder setTopShows(List<Show> topShows) {
        this.topShows = topShows;
        return this;
    }

    public List<Show> getNewReleases() {
        return newReleases;
    }

    public HomeScreenDataHolder setNewReleases(List<Show> newReleases) {
        this.newReleases = newReleases;
        return this;
    }

    public List<Show> getFollowedShows() {
        return followedShows;
    }

    public HomeScreenDataHolder setFollowedShows(List<Show> followedShows) {
        this.followedShows = followedShows;
        return this;
    }

    public List<Influencer> getFollowedInfluencers() {
        return followedInfluencers;
    }

    public HomeScreenDataHolder setFollowedInfluencers(List<Influencer> followedInfluencers) {
        this.followedInfluencers = followedInfluencers;
        return this;
    }

    public List<Video> getUnwatched() {
        return unwatched;
    }

    public HomeScreenDataHolder setUnwatched(List<Video> unwatched) {
        this.unwatched = unwatched;
        return this;
    }

    public List<Prize> getAlmostYours() {
        return almostYours;
    }

    public HomeScreenDataHolder setAlmostYours(List<Prize> almostYours) {
        this.almostYours = almostYours;
        return this;
    }

    public List<CategorizedShow> getCategories() {
        return categories;
    }

    public HomeScreenDataHolder setCategories(List<CategorizedShow> categories) {
        this.categories = categories;
        return this;
    }
}
