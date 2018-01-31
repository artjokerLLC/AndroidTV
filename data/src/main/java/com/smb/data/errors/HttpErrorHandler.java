package com.smb.data.errors;

/**
 * Created by dev on 30.01.18.
 */

public interface HttpErrorHandler {
    void handleError(Throwable throwable);
}
