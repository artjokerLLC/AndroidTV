package com.smb.data.mappers.instances

import com.apollographql.apollo.api.Response
import com.smb.core.models.Show
import com.smb.data.mappers.*
import data.*

class ShowMapper {
    companion object {
        fun map(data: Response<ShowQuery.Data>): Show {
            return show.invoke(data.data()?.show()?.fragments()?.showInfo()!!)
        }

        fun mapShows(data: List<ShowsQuery.Show>): List<com.smb.core.models.Show> {
            return fullShow.asListMapper().invoke(data)
        }

        fun map(data: List<TopShowsQuery.TopShow>): List<com.smb.core.models.Show> {
            return topShow.asListMapper().invoke(data)
        }

        fun mapOrdered(data: List<OrderedShowsQuery.Show>): List<com.smb.core.models.Show> {
            return orderedShows.asListMapper().invoke(data)
        }

        fun mapFollowed(data: List<FollowedShowQuery.SubShow>): List<com.smb.core.models.Show> {
            return followedShows.asListMapper().invoke(data)
        }

        fun mapRecommended(data: List<RecommendedShowsQuery.RecommendedShow>): List<com.smb.core.models.Show> {
            return recommendedShows.asListMapper().invoke(data)
        }
    }
}