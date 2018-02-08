package com.smb.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Media implements Parcelable{
    String cover;
    String hls;

    public Media(String cover, String hls) {
        this.cover = cover;
        this.hls = hls;
    }

    protected Media(Parcel in) {
        cover = in.readString();
        hls = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cover);
        dest.writeString(hls);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }
}
