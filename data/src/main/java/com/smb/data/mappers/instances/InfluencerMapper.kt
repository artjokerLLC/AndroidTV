package com.smb.data.mappers.instances

import com.smb.core.models.CategorizedInfluence
import com.smb.core.models.complex.Influencer
import com.smb.data.mappers.categoryInfluencer
import com.smb.data.mappers.creatorInfluencer
import com.smb.data.mappers.followedInfluencer
import com.smb.data.mappers.recommendedInfluencer
import data.FollowedInfluencerQuery
import data.InfluenceByTypeQuery
import data.InfluencersCategoriesQuery
import data.RecommendedInfluencersQuery

/**
 * Created by dev on 22.02.18.
 */

class InfluencerMapper {
    companion object {
        fun map(data: FollowedInfluencerQuery.Data?): List<Influencer> {
            return followedInfluencer.asListMapper().invoke(data?.profile()?.subInfluence())
        }

        fun mapRecommended(data: RecommendedInfluencersQuery.Data?): List<Influencer> {
            return recommendedInfluencer.asListMapper().invoke(data?.recommendedInfluences())
        }

        fun mapCreators(data: InfluenceByTypeQuery.Data?): List<Influencer> {
            return creatorInfluencer.asListMapper().invoke(data?.influences())
        }

        fun mapCategories(data: InfluencersCategoriesQuery.Data?): List<CategorizedInfluence> {
            return categoryInfluencer.asListMapper().invoke(data?.category())
        }
    }
}
