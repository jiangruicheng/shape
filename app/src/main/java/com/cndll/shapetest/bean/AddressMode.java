package com.cndll.shapetest.bean;

import android.databinding.Bindable;

import com.cndll.shapetest.BR;

/**
 * Created by jiangruicheng on 2017/8/18.
 */

public class AddressMode extends BaseBinding {
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
        notifyPropertyChanged(BR.tel);
    }

    @Bindable

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    public String name;
    public String tel;
    public String address;

    @Bindable
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
        notifyPropertyChanged(BR.area);
    }

    public String area;
}
