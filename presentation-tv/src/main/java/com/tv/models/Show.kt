package com.tv.models

import android.os.Parcel
import android.os.Parcelable

class Show() : Parcelable {

    var id: String? = null
    var title: String? = null
    var description: String? = null
    var cover: String? = null
    var videos: ArrayList<Video> = ArrayList()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        title = parcel.readString()
        description = parcel.readString()
        cover = parcel.readString()
        videos = parcel.createTypedArrayList(Video)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(cover)
        parcel.writeTypedList(videos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Show> {
        override fun createFromParcel(parcel: Parcel): Show {
            return Show(parcel)
        }

        override fun newArray(size: Int): Array<Show?> {
            return arrayOfNulls(size)
        }
    }
}