package com.smb.data.authentication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.smb.data.authentication.networks.Facebook;
import com.smb.data.authentication.networks.SocialNetwork;
import com.smb.data.models.SocialLoginResult;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.smb.data.authentication.SocialNetworkType.FACEBOOK;

/**
 * Created by dev on 25.01.18.
 */

public class SocialNetworkProvider {
    private Map<SocialNetworkType, SocialNetwork> socialNetworks = new HashMap<>();
    private Context context;

    @Inject
    public SocialNetworkProvider(Context context) {
        this.context = context;
    }

    public Observable<SocialLoginResult> loginBy(SocialNetworkType socialNetworkType, Activity activity) {
        switch (socialNetworkType) {
            case FACEBOOK:
                return facebookLogin(activity);
            default:
                return Observable.empty();
        }
    }

    private Observable<SocialLoginResult> facebookLogin(Activity activity) {
        SocialNetwork facebook = socialNetworks.get(FACEBOOK);
        if (facebook == null) {
            facebook = new Facebook(context);
            socialNetworks.put(FACEBOOK, facebook);
        }
        return facebook.login(activity)
                .subscribeOn(Schedulers.io());

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (Map.Entry<SocialNetworkType, SocialNetwork> entry : socialNetworks.entrySet()) {
            entry.getValue().postActivityResult(requestCode, resultCode, data);
        }
    }
}
