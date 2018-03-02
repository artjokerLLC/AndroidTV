package com.smb.data.mappers

import com.smb.core.mappers.Transformer
import com.smb.core.models.*
import com.smb.core.models.Banner
import com.smb.core.models.complex.Influencer
import data.*
import data.fragment.*
import guest.fragment.UserInformation


/**
 * It is only part of fields
 */

val fullShow = Transformer.build<ShowsQuery.Show, Show> {
    val fullShow = show.invoke(fragments().showInfo())
    fullShow.categories = category.asListMapper().invoke(categories())
    fullShow.video = fullVideo.asListMapper().invoke(video())
    fullShow
}

val fullVideo = Transformer.build<ShowsQuery.Video, Video> {
    val fullVideo = video.invoke(fragments().videoInfo())
    fullVideo.chapters = fullChapter.asListMapper().invoke(chapter())
    fullVideo
}

val fullChapter = Transformer.build<ShowsQuery.Chapter, Chapter> {
    val fullChapter = chapter.invoke(fragments().chapterInfo())
    media()?.fragments()?.mediaInfo()?.let {
        fullChapter.media = media.invoke(it)
    }
    fullChapter
}

val category = Transformer.build<ShowsQuery.Category, Category> {
    val categoryInfo = fragments().categoryInfo()
    val category = Category(categoryInfo.id(), categoryInfo.name())
    category
}

val orderedShows = Transformer.build<OrderedShowsQuery.Show, Show> {
    show.invoke(fragments().showInfo())
}
val recommendedShows = Transformer.build<RecommendedShowsQuery.RecommendedShow, Show> {
    show.invoke(fragments().showInfo())
}
val followedShows = Transformer.build<FollowedShowQuery.SubShow, Show> {
    show.invoke(fragments().showInfo())
}
val topShow = Transformer.build<TopShowsQuery.TopShow, Show> {
    show.invoke(fragments().showInfo())
}

val unwatchedVideos = Transformer.build<UnwatchedVideosQuery.Unwatched, Video> {
    video.invoke(fragments().videoInfo())
}

val banner = Transformer.build<BannersQuery.Magichour, Banner> {
    Banner(fragments().banner().id(), fragments().banner().banner())
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

val influencers = Transformer.build<InfluencersCategoriesQuery.Influence, InfluencerInfo> {
    fragments().influencerInfo()
}
val followedInfluencer = Transformer.build<FollowedInfluencerQuery.SubInfluence, Influencer> {
    influencer.invoke(fragments().influencerInfo())
}
val recommendedInfluencer = Transformer.build<RecommendedInfluencersQuery.RecommendedInfluence, Influencer> {
    influencer.invoke(fragments().influencerInfo())
}
val creatorInfluencer = Transformer.build<InfluenceByTypeQuery.Influence, Influencer> {
    influencer.invoke(fragments().influencerInfo())
}
val categoryInfluencer = Transformer.build<InfluencersCategoriesQuery.Category, CategorizedInfluence> {
    val category = categoryInfo.invoke(fragments().categoryInfo())
    val categorizedInfluence = CategorizedInfluence(category)
    val infos = influencers.asListMapper().invoke(influences())
    val influencers = influencer.asListMapper().invoke(infos)
    categorizedInfluence.influencers = influencers
    categorizedInfluence

}
val topInfluencer = Transformer.build<TopInfluencersQuery.TopInfluence, TopInfluencer> {
    val influencerInfo = fragments().influencerInfo()
    val influencer = influencer.invoke(influencerInfo)
    TopInfluencer(influencer)
}

/**
 * Simple mappers
 */
val chapter = Transformer.build<ChapterInfo, Chapter> {
    val chapter = Chapter()
    chapter.id = id()
    chapter.caratsEarned = carats_earned()
    chapter.isAbused = is_abused
    chapter.progress = progress()
    chapter.publish = publish()
    chapter.title = title()
    chapter.typename = __typename()
    chapter
}

val media = Transformer.build<MediaInfo, Media> {
    val media = Media()
    media.cover = cover()
    media.duration = duration()
    media.hash = hash()
    media.hls = hls()
    media.trailer = trailer()
    media.typename = __typename()
    media
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

val show = Transformer.build<ShowInfo, Show> {
    val show = com.smb.core.models.Show()
    show.title = title()
    show.description = description()
    show.cover = cover()
    show.id = id()
    show
}
val categoryInfo = Transformer.build<CategoryInfo, Category> {
    Category(id(), name())
}

val video = Transformer.build<VideoInfo, Video> {
    val video = com.smb.core.models.Video(id())
    video.title = title()
    video.description = description()
    video.publish = publish()
    video.summary = summary() ?: ""
    video.episode = episode()
    video.cover = cover() ?: ""
    video
}

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


