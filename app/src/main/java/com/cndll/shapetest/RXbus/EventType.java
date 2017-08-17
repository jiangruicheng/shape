package com.cndll.shapetest.RXbus;

import android.os.Bundle;

/**
 * Created by kongqing on 2017/5/9.
 */

public class EventType {
    public final static int LISTTAB = 0;
    public final static int LISTCLASS = 1;

    public int getType() {
        return type;
    }

    public EventType setType(int type) {
        this.type = type;
        return this;
    }

    public String getExtra() {
        return extra;
    }

    public EventType setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public Bundle getExtras() {
        return extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    private Bundle extras;
    private String extra;
    private int type;
}
