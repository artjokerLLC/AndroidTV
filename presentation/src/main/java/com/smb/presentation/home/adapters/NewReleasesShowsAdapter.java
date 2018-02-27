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

public class NewReleasesShowsAdapter extends AbstractPreviewAdapter {
    private List<Show> releases;

    public NewReleasesShowsAdapter(List<Show> releases) {
        super();
        this.releases = releases;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_new_releases));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Show show = releases.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.imageView, show);
    }

    @Override
    public int getItemCount() {
        return releases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageRelease);

        }
    }
}
