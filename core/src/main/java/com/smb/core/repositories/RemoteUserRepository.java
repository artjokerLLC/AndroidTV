package com.smb.core.repositories;


import com.smb.core.models.User;
import com.smb.core.models.holders.SocialLoginResult;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

/**
 * Created by dev on 31.01.18.
 */

public interface RemoteUserRepository {
    Observable<User> getUser(@NotNull SocialLoginResult socialData);
}
