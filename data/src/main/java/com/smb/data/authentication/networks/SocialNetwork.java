package com.smb.data.authentication.networks;

import android.app.Activity;

import com.smb.core.models.SocialLoginResult;

import io.reactivex.Observable;

/**
 * Created by dev on 25.01.18.
 */

public interface SocialNetwork extends ActivityLifecycle {

    Observable<SocialLoginResult> login(Activity activity);

    long getTypeId();

}
