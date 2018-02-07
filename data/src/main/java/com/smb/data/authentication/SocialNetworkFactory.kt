package com.smb.data.authentication

import android.content.Context
import com.smb.data.authentication.SocialNetworkType.*

import com.smb.data.authentication.networks.Facebook
import com.smb.data.authentication.networks.SocialNetwork
import com.smb.data.authentication.networks.google.Google
import com.smb.data.authentication.networks.instagram.Instagram
import com.smb.data.authentication.networks.twitter.Twitter

/**
 * Created by dev on 01.02.18.
 */

object SocialNetworkFactory {

    operator fun get(context: Context, type: SocialNetworkType): SocialNetwork {
        return when (type) {
            TWITTER -> Twitter(context)
            FACEBOOK -> Facebook(context)
            INSTAGRAM -> Instagram(context)
            GOOGLE -> Google(context)
            else -> throw IllegalArgumentException("Invalid social network type " + type.name + " id : " + type.code())
        }

    }
}
