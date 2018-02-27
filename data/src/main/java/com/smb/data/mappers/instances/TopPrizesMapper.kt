package com.smb.data.mappers.instances

import com.smb.core.models.Prize
import com.smb.data.mappers.topPrizes
import data.TopPrizesQuery

/**
 * Created by dev on 20.02.18.
 */

class TopPrizesMapper {

    companion object {
        fun map(data: TopPrizesQuery.Data?): List<Prize> {
            return topPrizes.asListMapper().invoke(data?.topPrize())
        }
    }
}
