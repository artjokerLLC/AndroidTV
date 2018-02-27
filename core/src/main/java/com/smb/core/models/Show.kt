package com.smb.core.models

import com.smb.core.models.base.Represantable

class Show(var id: String? = null,
           var title: String? = null,
           var description: String? = null,
           var cover: String? = null) : Represantable {

    var categories: List<Category>? = null

    override fun getIcon() = cover

    override fun toString(): String {
        return "Show(id=$id, title=$title, description=$description, cover=$cover)"
    }
}