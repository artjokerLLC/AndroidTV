package com.smb.ui.show;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.R;
import com.smb.data.models.Show;

public class ShowActivity extends MvpActivity implements ShowView {

    private static final String SHOW_ID_TAG = "SHOW_ID_TAG";

    @InjectPresenter
    ShowPresenter showPresenter;

    ProgressBar progressBar;

    public static Intent getIntent(final Context context, String showId) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.putExtra(SHOW_ID_TAG, showId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initViews();
        showPresenter.getShow(getIntent().getStringExtra(SHOW_ID_TAG));
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void showFragment(Show show) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.show_fragment_container,
                        ShowFragment.newInstance(show),
                        ShowFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
