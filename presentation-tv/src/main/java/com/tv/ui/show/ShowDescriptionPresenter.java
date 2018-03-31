package com.tv.ui.show;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tv.R;
import com.tv.models.Show;

import javax.inject.Inject;

public class ShowDescriptionPresenter extends Presenter {

    private Context mContext;

    @Inject
    public ShowDescriptionPresenter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.show_details, null);
        return new DescriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Show show = (Show) item;
        DescriptionViewHolder descriptionViewHolder = (DescriptionViewHolder) viewHolder;
        descriptionViewHolder.title.setText(show.getTitle());
        descriptionViewHolder.description.setText(show.getDescription());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        // Nothing to do here.
    }

    public class DescriptionViewHolder extends ViewHolder {

        TextView title;
        TextView subtitle;
        TextView description;

        public DescriptionViewHolder(View view) {
            super(view);
            initViews(view);
        }

        private void initViews(View view) {
            title = view.findViewById(R.id.title);
            subtitle = view.findViewById(R.id.subtitle);
            description = view.findViewById(R.id.description);
        }
    }
}
