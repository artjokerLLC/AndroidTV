package com.smb.core.models.complex

import com.smb.core.models.base.Represantable

/**
 * Created by dev on 21.02.18.
 */

open class Influencer(override val image: String,
                      override val name: String,
                      override val id: String,
                      override val lastName: String,
                      override val firstName: String) : InfluencerContract

interface InfluencerContract : Represantable {
    val image: String
    val name: String
    val id: String
    val lastName: String
    val firstName: String

    override fun getIcon() = image
}
