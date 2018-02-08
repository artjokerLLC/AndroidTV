package com.smb.data.models

import data.ShowsQuery
import java.io.Serializable

/**
 * Video class represents video entity with title, description, image thumbs and video url.
 */
data class Video(
        var id: Long = 0,
        var title: String? = null,
        var description: String? = null,
        var
        backgroundImageUrl: String? = null,
        var cardImageUrl: String? = null,
        var videoUrl: String? = null,
        var studio: String? = null,
        var category: String? = null
) : Serializable {

    var chapters: ArrayList<Chapter> = ArrayList()
    override fun toString(): String {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", backgroundImageUrl='" + backgroundImageUrl + '\'' +
                ", cardImageUrl='" + cardImageUrl + '\'' +
                '}'
    }

    companion object {
        internal const val serialVersionUID = 727566175075960653L
    }

    constructor(video: ShowsQuery.Video?) : this(0, video?.title(), video?.description(), video?.cover(), video?.cover(), null, null, null) {
        video?.chapter()?.forEach { iterator -> chapters.add(Chapter(iterator.title(), Media(iterator.media()?.cover(), iterator.media()?.hls()))) }
    }
}