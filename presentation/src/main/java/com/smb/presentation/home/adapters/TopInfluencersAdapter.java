package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smb.R;
import com.smb.core.models.TopInfluencer;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class TopInfluencersAdapter extends AbstractPreviewAdapter {
    private List<TopInfluencer> topInfluencers;

    public TopInfluencersAdapter(List<TopInfluencer> topInfluencers) {
        super();
        this.topInfluencers = topInfluencers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_top_influencer));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TopInfluencer topInfluencer = topInfluencers.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.imageView, topInfluencer);
        viewHolder.name.setText(topInfluencer.getName());
    }

    @Override
    public int getItemCount() {
        return topInfluencers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageTopInfluencer);
            name = itemView.findViewById(R.id.nameTextView);
        }
    }
}
