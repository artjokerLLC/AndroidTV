package com.smb.data.models;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.smb.data.mappers.UserMapper;

import guest.AuthorizeMutation;
import guest.RegisterMutation;

/**
 * Created by dev on 31.01.18.
 */

public class User {

    public static User create(Operation.Data data) {
        return new UserMapper(data).map();
    }
}
