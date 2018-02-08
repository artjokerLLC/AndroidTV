package com.smb.data.mappers

import com.smb.core.mappers.Transformer
import com.smb.data.models.User

import guest.fragment.UserInformation

/**
 * Created by dev on 07.02.18.
 */
val user = Transformer.build<UserInformation, User> {
    val user = User()
    user.avatar = avatar()
    user.email = email()
    user.gender = gender()
    user.id = id()
    user.nickname = nickname()
    user.token = token()
    user
}