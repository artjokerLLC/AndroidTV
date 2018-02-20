package com.smb.ui.show;

import com.arellomobile.mvp.MvpView;
import com.smb.data.models.Show;

interface ShowView extends MvpView{

    void showFragment(Show show);

    void showProgress();

    void hideProgress();
}
