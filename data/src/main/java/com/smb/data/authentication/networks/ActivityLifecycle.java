package com.smb.data.authentication.networks;

import android.content.Intent;

/**
 * Created by dev on 25.01.18.
 */

public interface ActivityLifecycle {
    void postActivityResult(int requestCode, int resultCode, Intent data);
}
