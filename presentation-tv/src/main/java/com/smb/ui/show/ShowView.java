package com.smb.ui.show;


import com.smb.models.Show;

public interface ShowView {
    void viewShow(Show show);
    void showProgress();
    void hideProgress();
}
