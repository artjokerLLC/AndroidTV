package com.smb.ui.show.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.R;
import com.smb.ui.show.ShowFragment;

public class ShowActivity extends MvpActivity implements ShowView {

    private static final String SHOW_ID_TAG = "SHOW_ID_TAG";

    @InjectPresenter
    ShowPresenter showPresenter;

    public static Intent getIntent(final Context context, String showId) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.putExtra(SHOW_ID_TAG, showId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        showPresenter.showFragments(getIntent().getStringExtra(SHOW_ID_TAG));
    }

    @Override
    public void showFragment(String showId) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.show_fragment_container,
                        ShowFragment.newInstance(showId),
                        ShowFragment.class.getSimpleName())
                .commit();
    }
}
