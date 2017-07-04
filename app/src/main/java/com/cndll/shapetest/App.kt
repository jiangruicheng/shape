package com.cndll.shapetest

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by jiangruicheng on 2017/7/3.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

    }
}