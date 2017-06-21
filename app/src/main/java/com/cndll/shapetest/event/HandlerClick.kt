package com.cndll.shapetest.event

import android.content.Intent
import android.view.View
import com.cndll.shapetest.activity.HomeActivity

/**
 * Created by kongqing on 2017/6/10.
 */

open class HandlerClick {
    fun login(view: View) {
        view.context.startActivity(Intent(view.context, HomeActivity::class.java))
    }
}
