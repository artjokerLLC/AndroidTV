package com.smb.data.repositories.implementation.user;


import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.smb.data.errors.ErrorMatcher;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.mappers.UserMapper;
import com.smb.data.models.SocialLoginResult;
import com.smb.data.models.User;
import com.smb.data.repositories.AbstractRemoteRepository;
import com.smb.data.repositories.api.RemoteUserRepository;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Inject;

import guest.AuthorizeMutation;
import guest.RegisterMutation;
import io.reactivex.Observable;

import static com.smb.data.http.graphql.GraphqlClientTypes.AUTHENTICATION;

/**
 * Created by dev on 24.01.18.
 */

public class GraphRemoteUserRepository extends AbstractRemoteRepository implements RemoteUserRepository {

    @Inject
    public GraphRemoteUserRepository(Map<GraphqlClientTypes, ApolloClient> apollo) {
        super(apollo.get(AUTHENTICATION));
    }

    @NotNull
    @Override
    public Observable<User> getUser(@NotNull SocialLoginResult socialData) {
        return register(socialData)
                .flatMap(registerResponse -> getUserObservable(socialData, registerResponse));
    }

    private Observable<Response<RegisterMutation.Data>> register(SocialLoginResult data) {
        RegisterMutation mutation = RegisterMutation.builder()
                .accessToken(data.getAccessToken())
                .avatarURL(data.getAvatarURL())
                .socialNetwork(data.getSocialNetwork())
                .userID(data.getUserID())
                .email(data.getEmail())
                .firebaseToken("1")
                .deviceToken("1")
                .nickname(data.getNickname())
                .promocode("")
                .build();
        return mutation(mutation);

    }

    private Observable<Response<AuthorizeMutation.Data>> authorize(SocialLoginResult socialData) {
        AuthorizeMutation mutation = AuthorizeMutation.builder()
                .deviceToken("1")
                .firebaseToken("1")
                .socialNetwork(socialData.getSocialNetwork())
                .userID(socialData.getUserID())
                .build();
        return mutation(mutation);

    }

    private Observable<User> getUserObservable(@NotNull SocialLoginResult socialData, Response<RegisterMutation.Data> registerResponse) {
        return ErrorMatcher.isError(registerResponse) ? authorize(socialData).map(UserMapper.Companion::getAuthorizedUser)
                : UserMapper.Companion.getRegisteredUser(registerResponse);
    }


}
