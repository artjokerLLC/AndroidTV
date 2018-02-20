package com.smb.data.authentication.networks.google;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.smb.core.models.SocialLoginResult;
import com.smb.data.RxBus;
import com.smb.data.authentication.networks.SocialNetwork;

import io.reactivex.Observable;

import static com.smb.data.authentication.SocialNetworkType.GOOGLE;

/**
 * Created by dev on 05.02.18.
 */

public class Google implements SocialNetwork {

    public static final int RC_SIGN_IN = 124;
    private Context context;
    private GoogleSignInClient googleSignInClient;
    private RxBus<SocialLoginResult> loginResultRxBus = new RxBus<>();

    public Google(Context context) {
        this.context = context;
        googleSignInClient = getGoogleClient(context);
    }

    private GoogleSignInClient getGoogleClient(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        return GoogleSignIn.getClient(context, gso);
    }

    @Override
    public void postActivityResult(int requestCode, int resultCode, Intent data) {
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public Observable<SocialLoginResult> login(Activity activity) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
        return loginResultRxBus.toObservable();
    }

    @Override
    public long getTypeId() {
        return GOOGLE.code();
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            SocialLoginResult result = new SocialLoginResult();
            result.setEmail(account.getEmail());
            result.setUserID(account.getId());
            result.setNickname(account.getDisplayName());
            Uri photoUrl = account.getPhotoUrl();
            if (photoUrl != null) {
                result.setAvatarURL(photoUrl.toString());
            }
            result.setSocialNetwork(getTypeId());
            result.setAccessToken(account.getIdToken());
            loginResultRxBus.send(result);
        } catch (ApiException e) {
            loginResultRxBus.error(e);
        }
    }
}
