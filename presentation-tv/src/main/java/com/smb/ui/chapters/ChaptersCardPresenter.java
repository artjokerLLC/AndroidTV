package com.smb.ui.chapters;

import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.smb.R;
import com.smb.data.models.Chapter;

import static android.support.v17.leanback.widget.ImageCardView.CARD_TYPE_FLAG_CONTENT;

public class ChaptersCardPresenter extends Presenter{

    private Drawable defaultCardImage;
    private int selectedBackgroundColor;
    private int defaultBackgroundColor;
    private int CARD_WIDTH = 313;
    private int CARD_HEIGHT = 176;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        defaultBackgroundColor = ContextCompat.getColor(parent.getContext(), R.color.default_background);
        selectedBackgroundColor =
                ContextCompat.getColor(parent.getContext(), R.color.selected_background);
        defaultCardImage = ContextCompat.getDrawable(parent.getContext(), R.drawable.movie);
        ImageCardView cardView = new ImageCardView(parent.getContext()){
            @Override
            public void setSelected(boolean selected) {
                updateCardBackgroundColor(this, selected);
                super.setSelected(selected);
            }
        };
        cardView.setCardType(CARD_TYPE_FLAG_CONTENT);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Chapter chapter = (Chapter)item;
        ImageCardView cardView = (ImageCardView)viewHolder.view;
        cardView.setTitleText(chapter.getTitle());
//        cardView.setContentText("Sample text");
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
        Glide.with(viewHolder.view.getContext())
                .load(chapter.getMedia() != null ? chapter.getMedia().getCover() : "")
                .centerCrop()
                .error(defaultCardImage)
                .into(cardView.getMainImageView());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        ImageCardView cardView = (ImageCardView)viewHolder.view;
        // Remove references to images so that the garbage collector can free up memory
        cardView.setBadgeImage(null);
        cardView.setMainImage(null);
    }

    private void updateCardBackgroundColor(ImageCardView view, Boolean selected) {
        int color = selected ? selectedBackgroundColor : defaultBackgroundColor;
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundColor(color);
        view.setInfoAreaBackgroundColor(color);
    }
}
