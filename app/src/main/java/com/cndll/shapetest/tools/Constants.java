package com.cndll.shapetest.tools;

import android.animation.ObjectAnimator;

import com.cndll.shapetest.view.StereoView;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public final class Constants {
    public static final String APP_ID = "wxd96b8dd3e5733967";
    public static final String APP_KEY="de1d138b686684274a39964f931b7eea";
    public static final String APPQQ_ID="1105155596";

    public static class ShowMsgActivity {
        public static final String STitle = "showmsg_title";
        public static final String SMessage = "showmsg_message";
        public static final String BAThumbData = "showmsg_thumb_data";
    }

    public static class Config {
        public static final boolean DEVELOPER_MODE = false;
    }

    // 验证手机
    public static Boolean validMobile(String pMobile) {
        if (pMobile == null
                || !Pattern.compile(Ini._REG_MOBILE).matcher(pMobile).matches()) {
            return false;
        }
        return pMobile.length() <= 0 ? false : true;
    }


    public static void startExitAnim(StereoView stereoView, int translateY) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(stereoView, "translationX", 0, 100, -translateY);
        animator.setDuration(500).start();
//        ToastUtil.showInfo(LoginActivity.this, "登录成功 =.=");
    }
}
