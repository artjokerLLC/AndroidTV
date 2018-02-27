package com.smb.data.mappers.instances

import com.smb.core.models.complex.Influencer
import com.smb.data.mappers.followedInfluencer
import data.FollowedInfluencerQuery

/**
 * Created by dev on 22.02.18.
 */

class InfluencerMapper {
    companion object {
        fun map(data: FollowedInfluencerQuery.Data?): List<Influencer> {
            return followedInfluencer.asListMapper().invoke(data?.profile()?.subInfluence())
        }
    }
}
