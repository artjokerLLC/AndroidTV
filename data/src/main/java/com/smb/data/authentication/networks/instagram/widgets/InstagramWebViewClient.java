package com.smb.data.authentication.networks.instagram.widgets;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.smb.data.authentication.networks.instagram.interfaces.InstagramAuthCallbackListener;


/**
 * Created by Sayyam on 3/18/16.
 */
public class InstagramWebViewClient extends WebViewClient {

    InstagramAuthCallbackListener instagramAuthCallbackListener;

    private InstagramWebViewClient() {
    }

    public InstagramWebViewClient(InstagramAuthCallbackListener instagramAuthCallbackListener) {
        this.instagramAuthCallbackListener = instagramAuthCallbackListener;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (!instagramAuthCallbackListener.onRedirect(url)) {
            view.loadUrl(url);
        }

        return true;
    }


}
