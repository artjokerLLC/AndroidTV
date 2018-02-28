package com.smb.models

import com.smb.data.models.Video
import com.smb.video
import data.ShowsQuery

class VideoMapper {
    companion object {
        fun map(data: ShowsQuery.Video): Video {
            return video.invoke(data)
        }
    }
}