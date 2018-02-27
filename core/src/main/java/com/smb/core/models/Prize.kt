package com.smb.core.models

import com.smb.core.models.base.Represantable

/**
 * Created by dev on 20.02.18.
 */

class Prize(
        val id: String,
        val name: String,
        val image: String,
        val price: Long,
        val description: String,
        val claimed: Long,
        val deliveryState: Boolean) : Represantable {

    var sponsor: Sponsor? = null
    var likeThis: List<Prize>? = null
    var shows: List<Show>? = null

    override fun getIcon() = image
}

