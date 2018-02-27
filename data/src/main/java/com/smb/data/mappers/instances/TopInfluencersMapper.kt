package com.smb.data.mappers.instances

import com.smb.core.models.TopInfluencer
import com.smb.data.mappers.topInfluencer
import data.TopInfluencersQuery

/**
 * Created by dev on 21.02.18.
 */

class TopInfluencersMapper {
    companion object {
        fun map(data: TopInfluencersQuery.Data?): List<TopInfluencer> {
            return topInfluencer.asListMapper().invoke(data?.topInfluence())
        }
    }
}
