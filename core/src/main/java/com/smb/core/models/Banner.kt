package com.smb.core.models

import com.smb.core.models.base.Represantable

/**
 * Created by dev on 20.02.18.
 */

data class Banner(val id: String, val image: String?) : Represantable {
    override fun getIcon() = image
}
