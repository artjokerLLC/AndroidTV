package com.smb;

import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CustomRowPresenter extends Presenter {
    public CustomRowPresenter(List<String> data) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lb_row_media_item, parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    public class ItemViewHolder extends ViewHolder {
        public ItemViewHolder(View view) {
            super(view);
        }
    }
}

