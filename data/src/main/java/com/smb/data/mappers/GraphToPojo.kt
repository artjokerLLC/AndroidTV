package com.smb.data.mappers

import com.smb.core.mappers.Transformer
import com.smb.core.models.*
import com.smb.core.models.Banner
import com.smb.core.models.complex.Influencer
import com.smb.data.models.Chapter
import com.smb.data.models.Media
import com.smb.data.models.Video
import data.*
import data.fragment.*
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
val show = Transformer.build<ShowInfo, com.smb.core.models.Show> {
    val show = com.smb.core.models.Show()
    show.title = title()
    show.description = description()
    show.cover = cover()
    show.id = id()
    show
}
//refactor
val showFromShowsQuery = Transformer.build<ShowsQuery.Show, Show> {
    val show = Show()
    val showInfo = fragments().showInfo()
    show.title = showInfo.title()
    show.description = showInfo.description()
    show.cover = showInfo.cover()
    show.id = showInfo.id()
    val categoryList: ArrayList<Category> = ArrayList()
    val categories = categories()
    categories.let {
        it?.forEach {
            val categoryInfo = it.fragments().categoryInfo()
            val id = categoryInfo.id()
            val name = categoryInfo.name()
            val category = Category(id, name)
            categoryList.add(category)
        }
    }
    show.categories = categoryList
    //video().let { it?.forEach { iterVideo -> show.videos.add(videoToRefactor.invoke(iterVideo)) } }
    show
}
val orderedShows = Transformer.build<OrderedShowsQuery.Show, com.smb.core.models.Show> {
    show.invoke(fragments().showInfo())
}
val followedShows = Transformer.build<FollowedShowQuery.SubShow, com.smb.core.models.Show> {
    show.invoke(fragments().showInfo())
}
val topShow = Transformer.build<TopShowsQuery.TopShow, com.smb.core.models.Show> {
    show.invoke(fragments().showInfo())
}

//refactor
val videoToRefactor = Transformer.build<ShowsQuery.Video, Video> {
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

val video = Transformer.build<VideoInfo, com.smb.core.models.Video> {
    val video = com.smb.core.models.Video(id())
    video.title = title()
    video.description = description()
    video.publish = publish()
    video.summary = summary() ?: ""
    video.episode = episode()
    video.cover = cover() ?: ""
    video
}
val unwatchedVideos = Transformer.build<UnwatchedVideosQuery.Unwatched, com.smb.core.models.Video> {
    video.invoke(fragments().videoInfo())
}
//refactor
val chapter = Transformer.build<ShowsQuery.Chapter, Chapter> {
    val chapter = Chapter()
    val chapterInfo = fragments().chapterInfo()
    chapter.title = chapterInfo.title()
    chapter.media = media.invoke(media()!!)
    chapter
}
//refactor
val media = Transformer.build<ShowsQuery.Media, Media> {
    val media = Media()
    val mediaInfo = fragments().mediaInfo()
    media.hls = mediaInfo.hls()
    media.cover = mediaInfo.cover()
    media
}
val banner = Transformer.build<BannersQuery.Magichour, Banner> {

    Banner(fragments().banner().id(), fragments().banner().banner())
}

val prize = Transformer.build<PrizeInfo, Prize> {
    val prize = Prize(id(),
            name(),
            image(),
            price(),
            description() ?: "",
            claimed() ?: 0,
            deliveryState() ?: false)

    prize

}
val sponsor = Transformer.build<SponsorInfo, Sponsor> {
    val sponsor = Sponsor()
            .apply {
                id = id()
                caratsEarned = carat_earned() ?: 0
                caratsaAvailable = carats_available() ?: 0
                isSubscribed = is_subscribe() ?: false
                logo = logo()
                name = name()

            }
    sponsor
}
val topPrizes = Transformer.build<TopPrizesQuery.TopPrize, Prize> {
    val prizeInfo = fragments().prizeInfo()
    val sponsorInfo = sponsor()?.fragments()?.sponsorInfo()
    val prizeModel = prize.invoke(prizeInfo)
    val sponsor = sponsorInfo?.let { sponsor.invoke(it) }
    prizeModel.sponsor = sponsor
    prizeModel
}
val almostTours = Transformer.build<AlmostYoursQuery.AlmostYour, Prize> {
    val prizeInfo = fragments().prizeInfo()
    val prizeModel = prize.invoke(prizeInfo)
    prizeModel
}
val influencer = Transformer.build<InfluencerInfo, Influencer> {
    val influencer = Influencer(avatar(), nickname(), id(),
            last_name() ?: "", first_name() ?: "")
    influencer
}
val followedInfluencer = Transformer.build<FollowedInfluencerQuery.SubInfluence, Influencer> {
    influencer.invoke(fragments().influencerInfo())
}
val topInfluencer = Transformer.build<TopInfluencersQuery.TopInfluence, TopInfluencer> {
    val influencerInfo = fragments().influencerInfo()
    val influencer = influencer.invoke(influencerInfo)
    TopInfluencer(influencer)
}

