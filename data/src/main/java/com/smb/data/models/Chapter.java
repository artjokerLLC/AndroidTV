package com.smb.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Chapter implements Parcelable{
    String title;
    Media media;

    public Chapter(String title, Media media) {
        this.title = title;
        this.media = media;
    }

    protected Chapter(Parcel in) {
        title = in.readString();
        media = in.readParcelable(Media.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeParcelable(media, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel in) {
            return new Chapter(in);
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
