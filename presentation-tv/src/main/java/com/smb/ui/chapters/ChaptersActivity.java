package com.smb.ui.chapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.smb.R;
import com.smb.data.models.Chapter;

import java.util.ArrayList;

public class ChaptersActivity extends Activity {

    private static final String CHAPTERS_TAG = "CHAPTERS_TAG";
    private static final String TITLE_TAG = "TITLE_TAG";
    private static final String EPISODE_COVER_TAG = "EPISODE_COVER_TAG";
    private FrameLayout container;

    public static Intent getIntent(final Context context, String title, String episodeCover, ArrayList<Chapter> chapters) {
        Intent intent = new Intent(context, ChaptersActivity.class);
        intent.putExtra(TITLE_TAG, title);
        intent.putExtra(EPISODE_COVER_TAG, episodeCover);
        intent.putParcelableArrayListExtra(CHAPTERS_TAG, chapters);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initChaptersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        container = findViewById(R.id.chapters_fragment_container);
    }

    private void initChaptersFragment() {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.chapters_fragment_container,
                        ChaptersFragment.newInstance(getIntent().getStringExtra(TITLE_TAG),
                                getIntent().getStringExtra(EPISODE_COVER_TAG),
                                getIntent().getParcelableArrayListExtra(CHAPTERS_TAG)),
                        ChaptersFragment.class.getSimpleName())
                .commit();
    }
}
