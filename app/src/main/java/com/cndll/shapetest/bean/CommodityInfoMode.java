package com.cndll.shapetest.bean;

import android.databinding.Bindable;

import com.cndll.shapetest.BR;
import com.cndll.shapetest.api.bean.response.CommodityResponse;

/**
 * Created by jiangruicheng on 2017/8/2.
 */

public class CommodityInfoMode extends BaseBinding {
    private String price;
    private String avatar;
    private String commodityCount;
    private CommodityResponse.DatasBean.GoodsBean choose;

    @Bindable
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public String getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(String commodityCount) {
        this.commodityCount = commodityCount;
        notifyPropertyChanged(BR.commodityCount);
    }

    @Bindable
    public CommodityResponse.DatasBean.GoodsBean getChoose() {
        return choose;
    }

    public void setChoose(CommodityResponse.DatasBean.GoodsBean choose) {
        this.choose = choose;
        notifyPropertyChanged(BR.choose);
    }

    @Bindable
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
        notifyPropertyChanged(BR.count);
    }

    private String count;
}
