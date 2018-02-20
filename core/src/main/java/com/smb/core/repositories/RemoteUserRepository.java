package com.smb.core.repositories;


import com.smb.core.models.SocialLoginResult;
import com.smb.core.models.User;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

/**
 * Created by dev on 31.01.18.
 */

public interface RemoteUserRepository {
    Observable<User> getUser(@NotNull SocialLoginResult socialData);
}
