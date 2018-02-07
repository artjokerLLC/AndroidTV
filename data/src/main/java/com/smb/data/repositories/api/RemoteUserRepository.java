package com.smb.data.repositories.api;

import com.smb.data.models.SocialLoginResult;
import com.smb.data.models.User;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

/**
 * Created by dev on 31.01.18.
 */

public interface RemoteUserRepository {
    Observable<User> getUser(@NotNull SocialLoginResult socialData);

}
