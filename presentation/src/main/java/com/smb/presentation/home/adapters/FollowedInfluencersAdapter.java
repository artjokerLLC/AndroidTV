package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smb.R;
import com.smb.core.models.complex.Influencer;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class FollowedInfluencersAdapter extends AbstractPreviewAdapter {
    private List<Influencer> followed;

    public FollowedInfluencersAdapter(List<Influencer> followed) {
        super();
        this.followed = followed;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_followed_influencers));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Influencer influencer = followed.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.imageView, influencer);
    }

    @Override
    public int getItemCount() {
        return followed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageFollowed);

        }
    }
}
