package com.smb.core.repositories;


import com.smb.core.models.User;

/**
 * Created by dev on 31.01.18.
 */

public interface LocalUserRepository {

    void put(User user);

    User get();
}