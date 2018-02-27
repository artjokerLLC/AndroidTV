package com.smb.data.mappers.instances

import com.apollographql.apollo.api.Response
import com.smb.core.models.User
import com.smb.data.mappers.user
import guest.AuthorizeMutation
import guest.RegisterMutation
import io.reactivex.Observable

/**
 * Created by dev on 31.01.18.
 */

class UserMapper {
    companion object {
        fun getAuthorizedUser(auth: Response<AuthorizeMutation.Data>): User {
            return user.invoke(auth.data()!!.auth()!!.fragments().userInformation())
        }

        fun getRegisteredUser(registerResponse: Response<RegisterMutation.Data>): Observable<User> {
            return Observable.just(user.invoke(registerResponse.data()!!.register()!!.fragments().userInformation()))
        }
    }
}