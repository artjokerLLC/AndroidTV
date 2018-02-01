package com.smb.data.repositories.implementation.user;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.smb.data.models.User;
import com.smb.data.repositories.api.LocalUserRepository;

import javax.inject.Inject;

/**
 * Created by dev on 31.01.18.
 */

public class SecureLocalUserRepository implements LocalUserRepository {

    public static final String USER = "user";
    private Context context;

    @Inject
    public SecureLocalUserRepository(Context context) {
        this.context = context;
        Hawk.init(context).build();
    }

    @Override
    public void put(User user) {
        String json = new Gson().toJson(user);
        Hawk.put(USER, json);

    }

    @Override
    public User get() {
        String json = Hawk.get(USER);
        return new Gson().fromJson(json, User.class);
    }
}
