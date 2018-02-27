package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smb.R;
import com.smb.core.models.Video;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class ContinueWatchingAdapter extends AbstractPreviewAdapter {
    private List<Video> videos;

    public ContinueWatchingAdapter(List<Video> videos) {
        super();
        this.videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_continue_watching));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Video video = videos.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.imageView, video);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageVideo);

        }
    }
}
