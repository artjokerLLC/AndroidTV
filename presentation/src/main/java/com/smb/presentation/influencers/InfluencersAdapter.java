package com.smb.presentation.influencers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smb.R;
import com.smb.core.models.complex.Influencer;
import com.smb.presentation.home.adapters.AbstractPreviewAdapter;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class InfluencersAdapter extends AbstractPreviewAdapter {
    protected List<Influencer> influencers;

    public InfluencersAdapter(List<Influencer> followed) {
        super();
        this.influencers = followed;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_influencers));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Influencer influencer = influencers.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.imageView, influencer);
        viewHolder.name.setText(influencer.getName());
    }

    @Override
    public int getItemCount() {
        return influencers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
