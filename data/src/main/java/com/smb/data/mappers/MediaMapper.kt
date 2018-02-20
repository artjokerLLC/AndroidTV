package com.smb.data.mappers

import com.smb.data.models.Media
import data.ShowsQuery

class MediaMapper{
    companion object {
        fun map(data : ShowsQuery.Media): Media {
            return media.invoke(data)
        }
    }
}