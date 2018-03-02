package com.smb.core.models.holders;

import com.smb.core.models.CategorizedInfluence;
import com.smb.core.models.complex.Influencer;

import java.util.List;

/**
 * Created by dev on 02.03.18.
 */

public class InfluencersDataHolder {
    private List<Influencer> recommended;
    private List<Influencer> followed;
    private List<Influencer> creators;
    private List<CategorizedInfluence> categorizedInfluences;

    public List<Influencer> getRecommended() {
        return recommended;
    }

    public InfluencersDataHolder setRecommended(List<Influencer> recommended) {
        this.recommended = recommended;
        return this;
    }

    public List<Influencer> getFollowed() {
        return followed;
    }

    public InfluencersDataHolder setFollowed(List<Influencer> followed) {
        this.followed = followed;
        return this;
    }

    public List<Influencer> getCreators() {
        return creators;
    }

    public InfluencersDataHolder setCreators(List<Influencer> creators) {
        this.creators = creators;
        return this;
    }

    public List<CategorizedInfluence> getCategorizedInfluences() {
        return categorizedInfluences;
    }

    public InfluencersDataHolder setCategorizedInfluences(List<CategorizedInfluence> categorizedInfluences) {
        this.categorizedInfluences = categorizedInfluences;
        return this;
    }
}
