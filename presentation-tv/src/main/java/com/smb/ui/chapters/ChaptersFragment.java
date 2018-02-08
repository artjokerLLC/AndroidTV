package com.smb.ui.chapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.VerticalGridFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.smb.R;
import com.smb.data.models.Chapter;
import com.smb.ui.player.PlaybackActivity;

import java.util.ArrayList;

public class ChaptersFragment extends VerticalGridFragment {

    private static final String CHAPTERS_TAG = "CHAPTERS_TAG";
    private static final String TITLE_TAG = "TITLE_TAG";
    private static final String EPISODE_COVER_TAG = "EPISODE_COVER_TAG";
    private static final int NUM_COLUMNS = 4;
    private ArrayObjectAdapter mAdapter;
    private Drawable mDefaultBackground;
    private DisplayMetrics metrics;
    private BackgroundManager mBackgroundManager;

    public static ChaptersFragment newInstance(String title, String episodeCover, ArrayList<Chapter> chapters) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(CHAPTERS_TAG, chapters);
        args.putString(TITLE_TAG, title);
        args.putString(EPISODE_COVER_TAG, episodeCover);
        ChaptersFragment fragment = new ChaptersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle();
        initBackground();
        initPresenter();
        initObjectAdapter();
        initListeners();
    }

    private void initListeners() {
        setOnItemViewClickedListener((itemViewHolder, item, rowViewHolder, row) -> {
            if (item instanceof Chapter) {
                startActivity(PlaybackActivity.getIntent(getActivity(), (Chapter) item));
            }
        });
    }

    private void initBackground() {
        prepareBackgroundManager();
        updateBackground(getArguments().getString(EPISODE_COVER_TAG));
    }

    private void initTitle() {
        setTitle(getArguments().getString(TITLE_TAG));
    }

    private void initObjectAdapter() {
        mAdapter = new ArrayObjectAdapter(new ChaptersCardPresenter());
        ArrayList<Chapter> chapters = getArguments().getParcelableArrayList(CHAPTERS_TAG);
        for (Chapter chapter : chapters) {
            mAdapter.add(chapter);
        }
        setAdapter(mAdapter);
    }

    private void initPresenter() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter();
        gridPresenter.setNumberOfColumns(NUM_COLUMNS);
        setGridPresenter(gridPresenter);
    }

    private void prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        mDefaultBackground = ContextCompat.getDrawable(getActivity(), R.drawable.default_background);
        metrics = getActivity().getResources().getDisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    private void updateBackground(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mBackgroundManager.setDrawable(new BitmapDrawable(getResources(), resource));
                    }
                });
    }
}
