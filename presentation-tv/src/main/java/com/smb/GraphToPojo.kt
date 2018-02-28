package com.smb

import com.smb.core.mappers.Transformer
import com.smb.core.models.Show
import com.smb.core.models.User
import com.smb.data.models.Chapter
import com.smb.data.models.Media
import com.smb.data.models.Video
import data.ShowQuery
import data.ShowsQuery
import guest.fragment.UserInformation

/**
 * Created by dev on 07.02.18.
 */
val user = Transformer.build<UserInformation, User> {
    val user = User()
    user.avatar = avatar()
    user.email = email()
    user.gender = gender()
    user.id = id()
    user.nickname = nickname()
    user.token = token()
    user
}

/**
 * It is only part of fields
 */
val show = Transformer.build<ShowQuery.Show, Show> {
    val show = Show()
    val showInfo = fragments().showInfo()
    show.title = showInfo.title()
    show.description = showInfo.description()
    show.cover = showInfo.cover()
    show.id = showInfo.id()
    show
}

//val showFromShowsQuery = Transformer.build<ShowsQuery.Show, Show> {
//    val show = Show()
//    val showInfo = fragments().showInfo()
//    show.title = showInfo.title()
//    show.description = showInfo.description()
//    show.cover = showInfo.cover()
//    show.id = showInfo.id()
//    video().let { it?.forEach { iterVideo -> show.video.add(video.invoke(iterVideo)) } }
//    show
//}

val video = Transformer.build<ShowsQuery.Video, Video> {
    val video = Video()
    val videoInfo = fragments().videoInfo()
    video.id = videoInfo.id()
    video.title = videoInfo.title()
    video.description = videoInfo.description()
    video.episode = videoInfo.episode()
    chapter().let { it?.forEach { iterChapter -> video.chapters.add(chapter.invoke(iterChapter)) } }
    video.cover = videoInfo.cover()
    video
}

val chapter = Transformer.build<ShowsQuery.Chapter, Chapter> {
    val chapter = Chapter()
    val chapterInfo = fragments().chapterInfo()
    chapter.title = chapterInfo.title()
    chapter.media = media.invoke(media()!!)
    chapter
}

val media = Transformer.build<ShowsQuery.Media, Media> {
    val media = Media()
    val mediaInfo = fragments().mediaInfo()
    media.hls = mediaInfo.hls()
    media.cover = mediaInfo.cover()
    media
}