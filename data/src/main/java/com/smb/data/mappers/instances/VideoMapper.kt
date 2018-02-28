package com.smb.data.mappers.instances


import com.smb.core.models.Video
import com.smb.data.mappers.fullVideo
import com.smb.data.mappers.unwatchedVideos
import data.ShowsQuery
import data.UnwatchedVideosQuery

class VideoMapper {
    companion object {
        fun map(data: ShowsQuery.Video): Video {
            return fullVideo.invoke(data)
        }

        fun map(data: UnwatchedVideosQuery.Data): List<com.smb.core.models.Video> {
            return unwatchedVideos.asListMapper().invoke(data.unwatched())
        }
    }
}