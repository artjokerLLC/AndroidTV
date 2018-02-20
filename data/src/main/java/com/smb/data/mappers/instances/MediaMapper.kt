package com.smb.data.mappers.instances

import com.smb.data.mappers.media
import com.smb.data.models.Media
import data.ShowsQuery

class MediaMapper{
    companion object {
        fun map(data : ShowsQuery.Media): Media {
            return media.invoke(data)
        }
    }
}