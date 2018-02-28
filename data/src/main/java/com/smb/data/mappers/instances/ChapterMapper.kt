package com.smb.data.mappers.instances

import com.smb.core.models.Chapter
import com.smb.data.mappers.fullChapter
import data.ShowsQuery

class ChapterMapper {
    companion object {
        fun map(data : ShowsQuery.Chapter): Chapter {
            return fullChapter.invoke(data)
        }
    }
}