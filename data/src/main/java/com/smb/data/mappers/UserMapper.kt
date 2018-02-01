package com.smb.data.mappers

import com.apollographql.apollo.api.Response
import com.smb.data.models.User
import guest.AuthorizeMutation
import guest.RegisterMutation
import guest.fragment.UserInformation
import io.reactivex.Observable

/**
 * Created by dev on 31.01.18.
 */

class UserMapper(private val data: UserInformation) {

    fun map(): User {
        val user = User()
        user.avatar = data.avatar()
        user.email = data.email()
        user.gender = data.gender()
        user.id = data.id()
        user.nickname = data.nickname()
        user.token = data.token()
        return user
    }

    companion object {
        fun getAuthorizedUser(auth: Response<AuthorizeMutation.Data>): User {
            return User.create(auth.data()!!.auth()!!.fragments().userInformation())
        }

        fun getRegisteredUser(registerResponse: Response<RegisterMutation.Data>): Observable<User> {
            val userInformation = registerResponse.data()!!.register()!!.fragments().userInformation()
            return Observable.just(User.create(userInformation))
        }
    }
}
