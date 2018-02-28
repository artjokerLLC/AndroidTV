package com.smb.core.models

import com.smb.core.models.base.Represantable

/**
 * Created by dev on 26.02.18.
 */

data class Video(val id: String) : Represantable {
    var hash: String = ""
    var episode: Long = 0
    var title: String = ""
    var description: String = ""
    var publish: String = ""
    var cover: String = ""
    var cover_tv: String = ""
    var summary: String = ""
    var views: Long = 0
    var type: Int = 0
    var chapters : List<Chapter>? = null

    override fun getIcon() = cover
}
