package com.cndll.shapetest.bean;

import android.databinding.Bindable;

import com.cndll.shapetest.BR;

/**
 * Created by jiangruicheng on 2017/8/4.
 */

public class InfoMode extends BaseBinding {
    @Bindable
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
        notifyPropertyChanged(BR.info);
    }

    @Bindable
    public boolean ismBoolean() {
        return mBoolean;
    }

    public void setmBoolean(boolean mBoolean) {
        this.mBoolean = mBoolean;
        notifyPropertyChanged(BR.mBoolean);
    }

    public boolean mBoolean;
    private String info;

}
