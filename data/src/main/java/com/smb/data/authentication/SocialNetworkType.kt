package com.smb.data.authentication

import com.smb.core.authentication.SocialNetworkCodes

/**
 * Created by dev on 25.01.18.
 */

enum class SocialNetworkType : SocialNetworkCodes {
    FACEBOOK {
        override fun code(): Int {
            return 1
        }
    },
    TWITTER {
        override fun code(): Int {
            return 2
        }
    },
    GOOGLE {
        override fun code(): Int {
            return 3
        }
    },
    INSTAGRAM {
        override fun code(): Int {
            return 4
        }
    }
}
