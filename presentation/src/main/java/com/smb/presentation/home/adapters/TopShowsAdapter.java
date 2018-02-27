package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smb.R;
import com.smb.core.models.Show;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class TopShowsAdapter extends AbstractPreviewAdapter {
    private List<Show> topShows;

    public TopShowsAdapter(List<Show> topShows) {
        super();
        this.topShows = topShows;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_top_show));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Show show = topShows.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.imageView, show);
    }

    @Override
    public int getItemCount() {
        return topShows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageTopShow);

        }
    }
}
