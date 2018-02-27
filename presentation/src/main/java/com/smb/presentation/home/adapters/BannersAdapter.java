package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smb.R;
import com.smb.core.models.Banner;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class BannersAdapter extends AbstractPreviewAdapter {
    private List<Banner> banners;

    public BannersAdapter(List<Banner> banners) {
        super();
        this.banners = banners;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_banner));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Banner banner = banners.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.banner, banner);
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView banner;

        public ViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.imageBanner);
        }
    }
}
