package com.tv.models

import android.os.Parcel
import android.os.Parcelable

class Video() : Parcelable {

    var id: String? = null
    var title: String? = null
    var description: String? = null
    var cover: String? = null
    var coverTv: String? = null
    var episode: Long? = null
    var chapters: ArrayList<Chapter> = ArrayList()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        title = parcel.readString()
        description = parcel.readString()
        cover = parcel.readString()
        coverTv = parcel.readString()
        episode = parcel.readValue(Int::class.java.classLoader) as? Long
        chapters = parcel.createTypedArrayList(Chapter)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(cover)
        parcel.writeString(coverTv)
        parcel.writeValue(episode)
        parcel.writeTypedList(chapters)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }
}