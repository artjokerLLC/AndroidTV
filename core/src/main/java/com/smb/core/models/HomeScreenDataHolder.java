package com.smb.core.models;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class HomeScreenDataHolder {
    private List<Banner> banners;
    private List<Prize> prizes;

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
}
