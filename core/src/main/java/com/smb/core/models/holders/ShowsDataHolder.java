package com.smb.core.models.holders;

import com.smb.core.models.CategorizedShow;
import com.smb.core.models.Show;

import java.util.List;

/**
 * Created by dev on 02.03.18.
 */

public class ShowsDataHolder {
    private List<Show> recommended;
    private List<Show> subscriptions;
    private List<CategorizedShow> categories;

    public List<Show> getRecommended() {
        return recommended;
    }

    public ShowsDataHolder setRecommended(List<Show> recommended) {
        this.recommended = recommended;
        return this;
    }

    public List<Show> getSubscriptions() {
        return subscriptions;
    }

    public ShowsDataHolder setSubscriptions(List<Show> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    public List<CategorizedShow> getCategories() {
        return categories;
    }

    public ShowsDataHolder setCategories(List<CategorizedShow> categories) {
        this.categories = categories;
        return this;
    }
}
