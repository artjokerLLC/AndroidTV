package com.smb.data.mappers.instances

import com.smb.data.mappers.chapter
import com.smb.data.models.Chapter
import data.ShowsQuery

class ChapterMapper {
    companion object {
        fun map(data : ShowsQuery.Chapter): Chapter {
            return chapter.invoke(data)
        }
    }
}