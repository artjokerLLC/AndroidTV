package com.smb.data.mappers.instances

import com.smb.core.models.Prize
import com.smb.data.mappers.almostTours
import data.AlmostYoursQuery

/**
 * Created by dev on 20.02.18.
 */

class AlmostYoursPrizesMapper {

    companion object {
        fun map(data: AlmostYoursQuery.Data?): List<Prize> {
            return almostTours.asListMapper().invoke(data?.almostYours())
        }
    }
}
