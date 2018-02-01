package com.smb.data.repositories.api;

import com.smb.data.models.User;

/**
 * Created by dev on 31.01.18.
 */

public interface LocalUserRepository {

    void put(User user);

    User get();
}
