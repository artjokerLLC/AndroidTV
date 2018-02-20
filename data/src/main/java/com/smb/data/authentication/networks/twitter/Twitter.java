package com.smb.data.authentication.networks.twitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.smb.core.models.SocialLoginResult;
import com.smb.data.authentication.networks.SocialNetwork;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import io.reactivex.Observable;
import retrofit2.Call;

import static com.smb.data.authentication.SocialNetworkType.TWITTER;

/**
 * Created by dev on 01.02.18.
 */

public class Twitter implements SocialNetwork {

    public static final String TWITTER_KEY = "e2JpOLFpHXgyP8mZGwCzhjomF";
    public static final String TWITTER_SECRET = "yqnYFcPnJfUSxHKlioCsbOWC1zcDpquFdZ9c3pizOhOJOGXoae";
    private final TwitterAuthClient twitterAuthClient;

    public Twitter(Context context) {
        super();
        TwitterConfig config = new TwitterConfig.Builder(context)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET))
                .debug(true)
                .build();
        com.twitter.sdk.android.core.Twitter.initialize(config);
        twitterAuthClient = new TwitterAuthClient();
    }

    @Override
    public Observable<SocialLoginResult> login(Activity activity) {
        SocialLoginResult socialLoginResult = new SocialLoginResult();
        return authorize(activity)
                .doOnNext(twitterSession -> {
                    socialLoginResult.setUserID(String.valueOf(twitterSession.getUserId()));
                    socialLoginResult.setNickname(twitterSession.getUserName());
                    socialLoginResult.setAccessToken(twitterSession.getAuthToken().token);
                    socialLoginResult.setSocialNetwork(getTypeId());
                })
                .flatMap(this::getUser)
                .doOnNext(user -> {
                    socialLoginResult.setEmail(user.email);
                    socialLoginResult.setAvatarURL(user.profileImageUrl);
                })
                .map(result -> socialLoginResult);
    }

    private Observable<User> getUser(TwitterSession session) {
        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient(session);
        AccountService accountService = apiClient.getAccountService();
        Call<User> userCall = accountService.verifyCredentials(true, false, true);
        return Observable.create(emitter -> userCall.enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> result) {
                emitter.onNext(result.data);
                emitter.onComplete();
            }

            @Override
            public void failure(TwitterException exception) {
                emitter.onError(exception);
            }
        }));
    }

    @Override
    public long getTypeId() {
        return TWITTER.code();
    }

    @Override
    public void postActivityResult(int requestCode, int resultCode, Intent data) {
        twitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

    private Observable<TwitterSession> authorize(Activity activity) {
        return Observable.create(emitter -> twitterAuthClient.authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                emitter.onNext(result.data);
                emitter.onComplete();
            }

            @Override
            public void failure(TwitterException exception) {
                emitter.onError(exception);
            }
        }));
    }
}
