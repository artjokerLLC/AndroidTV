package com.smb.data.authentication.networks

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.facebook.GraphResponse
import com.facebook.login.LoginResult
import com.smb.core.models.SocialLoginResult
import com.smb.data.authentication.SocialNetworkType.FACEBOOK
import com.u.rxfacebook.RxFacebook
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by dev on 25.01.18.
 */

class Facebook @Inject
constructor(val context: Context) : SocialNetwork {

    override fun login(activity: Activity): Observable<SocialLoginResult> {
        val rxFacebook = RxFacebook.create()
        var socialLoginResult = SocialLoginResult()
        return rxFacebook
                .loginWithReadPermissions(activity, permissions())
                .doOnNext { loginResult -> setupUser(loginResult, socialLoginResult) }
                .flatMap({ loginResult -> getUserInfo(rxFacebook, loginResult) }) { _, graphResponse -> graphResponse }
                .doOnNext { graphResponse -> parseFacebookUserData(graphResponse, socialLoginResult) }
                .map { socialLoginResult }

    }

    private fun setupUser(loginResult: LoginResult, socialLoginResult: SocialLoginResult) {
        val accessToken = loginResult.accessToken
        socialLoginResult.accessToken = accessToken.token
        socialLoginResult.userID = accessToken.userId
    }

    private fun parseFacebookUserData(graphResponse: GraphResponse, socialLoginResult: SocialLoginResult) {
        val jsonObject = graphResponse.jsonObject
        socialLoginResult.email = jsonObject.getString("email")
        socialLoginResult.nickname = jsonObject.getString("name")
        val picture = jsonObject.getJSONObject("picture")
        socialLoginResult.avatarURL = picture?.getJSONObject("data")?.getString("url")
        socialLoginResult.socialNetwork = typeId
    }

    private fun getUserInfo(rxFacebook: RxFacebook, loginResult: LoginResult): Observable<GraphResponse> {
        return rxFacebook.accessToken(loginResult.accessToken)
                .requestMe()
                .subscribeOn(Schedulers.io())
    }

    private fun permissions(): ArrayList<String> {
        val permissions = ArrayList<String>()
        permissions.add("email")
        return permissions
    }

    override fun getTypeId(): Long {
        return FACEBOOK.code().toLong()
    }

    override fun postActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        RxFacebook.postLoginActivityResult(requestCode, resultCode, data)
    }
}
