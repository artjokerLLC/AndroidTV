package com.smb.core.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("token")
    @Expose
    var token: String? = null
    @SerializedName("nickname")
    @Expose
    var nickname: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
    @SerializedName("gender")
    @Expose
    var gender: Any? = null
}

