package com.cndll.shapetest.bean;

import android.app.Fragment;
import android.databinding.Bindable;

import com.cndll.shapetest.BR;

import java.util.List;

/**
 * Created by kongqing on 2017/6/10.
 */

public class Home extends BaseBinding {
    @Bindable
    public List<String> getTabName() {
        return tabName;
    }

    public void setTabName(List<String> tabName) {
        this.tabName = tabName;
        notifyPropertyChanged(BR.tabName);
    }
@Bindable
    public List<Fragment> getPager() {
        return pager;
    }

    public void setPager(List<Fragment> pager) {
        this.pager = pager;
        notifyPropertyChanged(BR.pager);
    }

    List<String> tabName;
    List<Fragment> pager;
}
