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

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public List<TopInfluencer> getTopInfluencers() {
        return topInfluencers;
    }

    public void setTopInfluencers(List<TopInfluencer> topInfluencers) {
        this.topInfluencers = topInfluencers;
    }

    public List<Show> getTopShows() {
        return topShows;
    }

    public void setTopShows(List<Show> topShows) {
        this.topShows = topShows;
    }

    public List<Show> getNewReleases() {
        return newReleases;
    }

    public void setNewReleases(List<Show> newReleases) {
        this.newReleases = newReleases;
    }

    public List<Show> getFollowedShows() {
        return followedShows;
    }

    public void setFollowedShows(List<Show> followedShows) {
        this.followedShows = followedShows;
    }

    public List<Influencer> getFollowedInfluencers() {
        return followedInfluencers;
    }

    public void setFollowedInfluencers(List<Influencer> followedInfluencers) {
        this.followedInfluencers = followedInfluencers;
    }

    public List<Video> getUnwatched() {
        return unwatched;
    }

    public void setUnwatched(List<Video> unwatched) {
        this.unwatched = unwatched;
    }

    public List<Prize> getAlmostYours() {
        return almostYours;
    }

    public void setAlmostYours(List<Prize> almostYours) {
        this.almostYours = almostYours;
    }

    public List<CategorizedShow> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorizedShow> categories) {
        this.categories = categories;
    }
}
