package com.smb.presentation.shows;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.presentation.main.NavigationActivity;
import com.smb.presentation.main.NavigationPresenter;

public class ShowsRootActivity extends NavigationActivity implements ShowsView {
    public static final String TAG = "ShowsActivity";
    @InjectPresenter
    ShowsPresenter mShowsPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, ShowsRootActivity.class);

        return intent;
    }


    @Override
    public NavigationPresenter getNavigationPresenter() {
        return mShowsPresenter;
    }
}
