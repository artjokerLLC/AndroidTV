package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smb.R;
import com.smb.core.models.CategorizedShow;

import java.util.List;

/**
 * Created by dev on 27.02.18.
 */

public class GenresAdapter extends RecyclerView.Adapter {
    private List<CategorizedShow> categories;

    public GenresAdapter(List<CategorizedShow> categories) {
        super();
        this.categories = categories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.adapter_item_genre, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        CategorizedShow category = categories.get(position);
        viewHolder.name.setText(category.getCategory().getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);

        }
    }
}
