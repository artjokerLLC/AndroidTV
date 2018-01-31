package com.smb.data.errors;

import com.apollographql.apollo.api.Response;

/**
 * Created by dev on 30.01.18.
 */

public class ErrorMatcher {

    public static boolean isError(Response response) {
        return  response.errors().size() > 0;
    }
}
