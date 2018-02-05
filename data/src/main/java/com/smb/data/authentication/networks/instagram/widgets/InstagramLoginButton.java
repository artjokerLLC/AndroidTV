package com.smb.data.authentication.networks.instagram.widgets;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.smb.data.authentication.networks.instagram.activities.InstagramAuthActivity;
import com.smb.data.authentication.networks.instagram.engine.InstagramEngine;
import com.smb.data.authentication.networks.instagram.interfaces.InstagramLoginCallbackListener;


/**
 * Created by Sayyam on 3/17/16.
 */
public class InstagramLoginButton extends AppCompatButton {

    private final Context context;
    InstagramLoginCallbackListener instagramLoginButtonCallback;
    OnClickListener onClickListener;

    String[] scopes;

    public InstagramLoginButton(Context context) {
        super(context);
        this.context = context;
    }

    public InstagramLoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setupLoginButton();
    }

    public InstagramLoginButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setupLoginButton();
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }


    public void setInstagramLoginCallback(InstagramLoginCallbackListener loginCallbackListener) {
        this.instagramLoginButtonCallback = loginCallbackListener;

    }

    private void setupLoginButton() {
        super.setOnClickListener(new InstagramLoginButton.LoginClickListener());
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private class LoginClickListener implements OnClickListener {
        private LoginClickListener() {
        }

        public void onClick(View view) {

            this.checkCallback(InstagramLoginButton.this.instagramLoginButtonCallback);

            InstagramEngine.getInstance(context).setInstagramLoginButtonCallback(InstagramLoginButton.this.instagramLoginButtonCallback);

            Intent intent = new Intent(context, InstagramAuthActivity.class);
            intent.putExtra(InstagramEngine.TYPE, InstagramEngine.TYPE_LOGIN);
            intent.putExtra(InstagramEngine.IS_LOGIN_BUTTON, true);
            intent.putExtra(InstagramEngine.SCOPE, scopes);
//            intent.putExtra("type", 0);
//            intent.putExtra("scopes", scopes);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);

            context.startActivity(intent);

            if (InstagramLoginButton.this.onClickListener != null) {
                InstagramLoginButton.this.onClickListener.onClick(view);
            }

        }

        private void checkCallback(InstagramLoginCallbackListener callback) {
            if (callback == null) {

                throw new RuntimeException("Callback must not be null, did you call " + InstagramLoginCallbackListener.class.getName() + "?");

            }

        }

//        private void checkActivity(Activity activity) {
//            if(activity == null || activity.isFinishing()) {
//                CommonUtils.logOrThrowIllegalStateException("InstagramAPI", "TwitterLoginButton requires an activity. Override getActivity to provide the activity for this button.");
//            }
//        }
    }
}
