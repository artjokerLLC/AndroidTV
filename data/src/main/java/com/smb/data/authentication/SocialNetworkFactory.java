package com.smb.data.authentication;

import android.content.Context;
import android.support.annotation.NonNull;

import com.smb.data.authentication.networks.Facebook;
import com.smb.data.authentication.networks.SocialNetwork;
import com.smb.data.authentication.networks.instagram.Instagram;
import com.smb.data.authentication.networks.twitter.Twitter;

/**
 * Created by dev on 01.02.18.
 */

public class SocialNetworkFactory {

    @NonNull
    public static SocialNetwork get(Context context, SocialNetworkType type) {
        switch (type) {
            case TWITTER:
                return new Twitter(context);
            case FACEBOOK:
                return new Facebook(context);
            case INSTAGRAM:
                return new Instagram(context);
            default:
                throw new IllegalArgumentException("Invalid social network type " + type.name() + " id : " + type.code());
        }

    }
}
