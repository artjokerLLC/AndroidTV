package com.smb.core.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SocialLoginResult : Serializable {

    @SerializedName("socialNetwork")
    @Expose
    var socialNetwork: Long? = null
    @SerializedName("userID")
    @Expose
    var userID: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("accessToken")
    @Expose
    var accessToken: String? = null
    @SerializedName("nickname")
    @Expose
    var nickname: String? = null
    @SerializedName("avatarURL")
    @Expose
    var avatarURL: String? = null

}
