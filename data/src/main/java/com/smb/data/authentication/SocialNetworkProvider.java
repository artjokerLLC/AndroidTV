package com.smb.data.authentication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.smb.core.models.holders.SocialLoginResult;
import com.smb.data.authentication.networks.SocialNetwork;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dev on 25.01.18.
 */
@Singleton
public class SocialNetworkProvider {
    private Map<SocialNetworkType, SocialNetwork> socialNetworks = new HashMap<>();
    private Context context;

    @Inject
    public SocialNetworkProvider(Context context) {
        this.context = context;
    }

    public Observable<SocialLoginResult> loginBy(SocialNetworkType socialNetworkType, Activity activity) {
        return getSocialNetwork(socialNetworkType)
                .login(activity)
                .subscribeOn(Schedulers.io());
    }


    private SocialNetwork getSocialNetwork(SocialNetworkType type) {
        SocialNetwork socialNetwork = socialNetworks.get(type);
        if (socialNetwork == null) {
            socialNetwork = SocialNetworkFactory.INSTANCE.get(context, type);
            socialNetworks.put(type, socialNetwork);
        }
        return socialNetwork;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (Map.Entry<SocialNetworkType, SocialNetwork> entry : socialNetworks.entrySet()) {
            entry.getValue().postActivityResult(requestCode, resultCode, data);
        }
    }
}
