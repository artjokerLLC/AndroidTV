package com.smb.data.authentication.networks.instagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.smb.data.RxBus;
import com.smb.data.authentication.networks.SocialNetwork;
import com.smb.data.authentication.networks.instagram.activities.InstagramAuthActivity;
import com.smb.data.authentication.networks.instagram.engine.InstagramEngine;
import com.smb.data.authentication.networks.instagram.engine.InstagramKitConstants;
import com.smb.data.authentication.networks.instagram.exceptions.InstagramException;
import com.smb.data.authentication.networks.instagram.interfaces.InstagramAPIResponseCallback;
import com.smb.data.authentication.networks.instagram.objects.IGPagInfo;
import com.smb.data.authentication.networks.instagram.objects.IGSession;
import com.smb.data.authentication.networks.instagram.objects.IGUser;
import com.smb.data.authentication.networks.instagram.utils.InstagramKitLoginScope;
import com.smb.data.models.SocialLoginResult;

import io.reactivex.Observable;

import static android.app.Activity.RESULT_OK;
import static com.smb.data.authentication.SocialNetworkType.INSTAGRAM;

/**
 * Created by dev on 01.02.18.
 */

public class Instagram implements SocialNetwork {

    public static final int REQUEST_CODE = 123;
    private RxBus<SocialLoginResult> loginResultRxBus = new RxBus<>();
    private Context context;

    public Instagram(Context context) {

        this.context = context;
    }

    @Override
    public void postActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:

                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null && bundle.containsKey(InstagramKitConstants.kSessionKey)) {
                        IGSession session = (IGSession) bundle.getSerializable(InstagramKitConstants.kSessionKey);
                        if (session != null) {
                            getUserData(session);
                        }
                    }
                }
                break;
        }
    }

    private void getUserData(IGSession session) {
        SocialLoginResult socialLoginResult = new SocialLoginResult();
        socialLoginResult.setAccessToken(session.getAccessToken());
        socialLoginResult.setSocialNetwork(getTypeId());
        getUser(session)
                .doOnNext(igUser -> {
                    socialLoginResult.setAvatarURL(igUser.getProfilePictureURL());
                    String fullName = igUser.getFullName();
                    if (TextUtils.isEmpty(fullName)) {
                        fullName = igUser.getUsername();
                    }
                    //socialLoginResult.setEmail("");
                    socialLoginResult.setNickname(fullName);
                    socialLoginResult.setUserID(igUser.getId());
                })
                .subscribe(igUser -> loginResultRxBus.send(socialLoginResult));
    }

    private Observable<IGUser> getUser(IGSession session) {
        return Observable.create(emitter -> InstagramEngine.getInstance(context, session).getUserDetails(new InstagramAPIResponseCallback<IGUser>() {
            @Override
            public void onResponse(IGUser responseObject, IGPagInfo pageInfo) {
                emitter.onNext(responseObject);
                emitter.onComplete();
            }

            @Override
            public void onFailure(InstagramException exception) {
                emitter.onError(exception);
            }
        }));
    }

    @Override
    public Observable<SocialLoginResult> login(Activity activity) {
        String[] scopes = {InstagramKitLoginScope.BASIC};

        Intent intent = new Intent(activity, InstagramAuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);

        intent.putExtra(InstagramEngine.TYPE, InstagramEngine.TYPE_LOGIN);
        //add scopes if you want to have more than basic access
        intent.putExtra(InstagramEngine.SCOPE, scopes);

        activity.startActivityForResult(intent, REQUEST_CODE);
        return loginResultRxBus.toObservable();
    }

    @Override
    public long getTypeId() {
        return INSTAGRAM.code();
    }
}
