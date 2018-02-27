package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smb.core.models.base.Represantable;

/**
 * Created by dev on 21.02.18.
 */

public abstract class AbstractPreviewAdapter extends RecyclerView.Adapter {

    protected View getView(ViewGroup parent, int item) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(item, parent, false);
    }

    protected void loadImageTo(ImageView view, Represantable represantable) {
        Glide.with(view.getContext())
                .load(represantable.getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }
}
