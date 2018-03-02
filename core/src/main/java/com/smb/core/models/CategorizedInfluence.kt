package com.smb.core.models

import com.smb.core.models.complex.Influencer

/**
 * Created by dev on 26.02.18.
 */

class CategorizedInfluence(val category: Category) {
    var influencers: List<Influencer>? = null
}
