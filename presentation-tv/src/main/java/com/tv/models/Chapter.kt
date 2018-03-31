package com.tv.models

import android.os.Parcel
import android.os.Parcelable

class Chapter() : Parcelable {
    var title: String? = null
    var media: Media? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        media = parcel.readParcelable(Media::class.java.classLoader)
    }

    constructor(title: String, media: Media) : this() {
        this.title = title
        this.media = media
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeParcelable(media, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Chapter> {
        override fun createFromParcel(parcel: Parcel): Chapter {
            return Chapter(parcel)
        }

        override fun newArray(size: Int): Array<Chapter?> {
            return arrayOfNulls(size)
        }
    }
}
