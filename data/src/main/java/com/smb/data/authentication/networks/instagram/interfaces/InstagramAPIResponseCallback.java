package com.smb.data.authentication.networks.instagram.interfaces;


import com.smb.data.authentication.networks.instagram.exceptions.InstagramException;
import com.smb.data.authentication.networks.instagram.objects.IGPagInfo;

/**
 * Created by Sayyam on 3/18/16.
 */
public interface InstagramAPIResponseCallback<T> {

    public void onResponse(T responseObject, IGPagInfo pageInfo);

    public void onFailure(InstagramException exception);
}
