package com.smb.data.authentication;

/**
 * Created by dev on 25.01.18.
 */

public enum SocialNetworkType implements SocialNetworkCodes {
    FACEBOOK {
        @Override
        public int code() {
            return 1;
        }
    }, TWITTER {
        @Override
        public int code() {
            return 2;
        }
    }, GOOGLE {
        @Override
        public int code() {
            return 3;
        }
    }, INSTAGRAM {
        @Override
        public int code() {
            return 4;
        }
    }
}
