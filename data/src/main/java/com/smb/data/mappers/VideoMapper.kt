package com.smb.data.mappers

import com.smb.data.models.Video
import data.ShowsQuery

class VideoMapper {
    companion object {
        fun map(data : ShowsQuery.Video): Video {
            return video.invoke(data)
        }
    }
}