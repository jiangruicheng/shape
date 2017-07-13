package com.cndll.shapetest.tools;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;

/**
 * Created by jiangruicheng on 2017/7/12.
 */

public class StringTools {
    public static String getResUri(@DrawableRes int res, Context context) {
        return ("res://" + context.getPackageName() + "/" + res);
    }
}
