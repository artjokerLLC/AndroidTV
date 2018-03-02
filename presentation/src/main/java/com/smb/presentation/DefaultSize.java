package com.smb.presentation;

import android.content.Context;

import com.smb.R;
import com.smb.core.models.util.Size;

/**
 * Created by dev on 01.03.18.
 */

public class DefaultSize extends Size {
    public DefaultSize(Context context) {
        super(0, 0);
        int width = (int) context.getResources().getDimension(R.dimen.imageTopPrizeWidth);
        int height = (int) context.getResources().getDimension(R.dimen.imageTopPrizeHeight);
        setWidth(width);
        setHeight(height);
    }
}
