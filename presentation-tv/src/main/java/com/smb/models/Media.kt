package com.smb.models

import android.os.Parcel
import android.os.Parcelable

class Media() : Parcelable {
    var cover: String? = null
    var hls: String? = null

    constructor(cover: String, hls: String) : this() {
        this.cover = cover
        this.hls = hls
    }

    protected constructor(`in`: Parcel) : this() {
        cover = `in`.readString()
        hls = `in`.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(cover)
        dest.writeString(hls)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Media> {
        override fun createFromParcel(parcel: Parcel): Media {
            return Media(parcel)
        }

        override fun newArray(size: Int): Array<Media?> {
            return arrayOfNulls(size)
        }
    }
}
