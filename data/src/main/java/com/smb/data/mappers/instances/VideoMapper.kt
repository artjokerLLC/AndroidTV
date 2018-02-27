package com.smb.data.mappers.instances


import com.smb.data.mappers.unwatchedVideos
import com.smb.data.mappers.videoToRefactor
import com.smb.data.models.Video
import data.ShowsQuery
import data.UnwatchedVideosQuery

class VideoMapper {
    companion object {
        fun map(data: ShowsQuery.Video): Video {
            return videoToRefactor.invoke(data)
        }

        fun map(data: UnwatchedVideosQuery.Data): List<com.smb.core.models.Video> {
            return unwatchedVideos.asListMapper().invoke(data.unwatched())
        }
    }
}