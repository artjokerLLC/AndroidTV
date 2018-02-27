package com.smb.data.mappers.instances

import com.smb.core.models.Banner
import com.smb.data.mappers.banner
import data.BannersQuery

/**
 * Created by dev on 20.02.18.
 */

class BannerMapper {
    companion object {
        fun map(data: BannersQuery.Data?): List<Banner> {
            return banner.asListMapper().invoke(data?.magichours())
        }
    }
}
