package com.cndll.shapetest.bean;

import android.databinding.Bindable;

import com.cndll.shapetest.BR;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/22.
 */

public class ShopInfoMode extends BaseBinding {
    public String logoUrl;

    @Bindable
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        notifyPropertyChanged(BR.logoUrl);
    }

    @Bindable

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
        notifyPropertyChanged(BR.shopName);
    }

    @Bindable

    public String getSalenum() {
        return salenum;
    }

    public void setSalenum(String salenum) {
        this.salenum = salenum;
        notifyPropertyChanged(BR.salenum);
    }

    @Bindable

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        notifyPropertyChanged(BR.count);
    }

    public String shopName;
    public String salenum;
    public int count;

    public List<Goods> getUrls() {
        return urls;
    }

    public void setUrls(List<Goods> urls) {
        this.urls = urls;
    }

    public List<Goods> urls;

    public static class Goods {
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        String url;
        String id;
    }
}
