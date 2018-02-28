package com.smb.presentation.shows;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.R;
import com.smb.base.BaseFragment;

public class ShowsFragment extends BaseFragment implements ShowsView {
    public static final String TAG = "ShowsFragment";
    @InjectPresenter
    ShowsPresenter mShowsPresenter;

    public static ShowsFragment newInstance() {
        ShowsFragment fragment = new ShowsFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_shows;
    }

    @Override
    public void onPresenterReady() {

    }
}
