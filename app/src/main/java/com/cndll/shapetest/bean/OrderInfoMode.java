package com.cndll.shapetest.bean;

import android.databinding.Bindable;

import com.cndll.shapetest.BR;

/**
 * Created by jiangruicheng on 2017/8/18.
 */

public class OrderInfoMode extends BaseBinding {
    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    String title;

    @Bindable
    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
        notifyPropertyChanged(BR.orderInfo);
    }

    String orderInfo;
}
