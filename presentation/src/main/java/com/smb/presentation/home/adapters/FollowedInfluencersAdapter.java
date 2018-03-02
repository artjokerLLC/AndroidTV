package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;

import com.smb.core.models.complex.Influencer;
import com.smb.presentation.influencers.InfluencersAdapter;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class FollowedInfluencersAdapter extends InfluencersAdapter {
    public FollowedInfluencersAdapter(List<Influencer> followed) {
        super(followed);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Influencer influencer = influencers.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.imageView, influencer);
    }
}
