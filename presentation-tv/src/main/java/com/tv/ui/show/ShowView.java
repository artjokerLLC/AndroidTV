package com.tv.ui.show;


import com.tv.models.Show;

public interface ShowView {
    void viewShow(Show show);
    void showProgress();
    void hideProgress();
}
