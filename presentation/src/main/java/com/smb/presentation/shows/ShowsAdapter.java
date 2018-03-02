package com.smb.presentation.shows;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smb.R;
import com.smb.core.models.Show;
import com.smb.presentation.home.adapters.AbstractPreviewAdapter;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class ShowsAdapter extends AbstractPreviewAdapter {
    private List<Show> shows;

    public ShowsAdapter(List<Show> shows) {
        super();
        this.shows = shows;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_show));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Show show = shows.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.imageView, show);
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageTopShow);

        }
    }
}
