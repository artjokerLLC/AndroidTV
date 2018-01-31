package com.smb.data.errors;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by dev on 30.01.18.
 */

public class RxErrorHandlers {

    public static <T> ObservableTransformer<T, T> applyHttpErrors(HttpErrorHandler httpErrorHandler) {
        return upstream -> upstream.onErrorResumeNext((Function<Throwable, ObservableSource<? extends T>>) throwable -> {
            httpErrorHandler.handleError(throwable);
            return Observable.empty();
        });
    }
}
