package com.cndll.shapetest.event

import android.content.Intent
import android.os.Build
import android.view.View
import com.cndll.shapetest.activity.HomeActivity
import com.cndll.shapetest.activity.SignActivity
import android.os.Bundle
import com.cndll.shapetest.activity.TurnOnActivity


/**
 * Created by kongqing on 2017/6/10.
 */

open class HandlerClick {
    fun login(view: View) {
        view.context.startActivity(Intent(view.context, HomeActivity::class.java))
    }

    fun sign(view: View) {
        val bundle = Bundle()
        bundle.putString("type", "sign")
        view.context.startActivity(Intent(view.context, SignActivity::class.java).putExtras(bundle))
    }

    fun updatePwd(view: View) {
        val bundle = Bundle()
        bundle.putString("type", "pwd")
        view.context.startActivity(Intent(view.context, SignActivity::class.java).putExtras(bundle))
    }

    open fun turnOnFragment(view: View, flag: String, bundle: Bundle?) {
        var b = bundle
        if (b == null) {
            b = Bundle()
        }
        view.context.startActivity(Intent(view.context, TurnOnActivity::class.java).putExtras(b).setAction(flag))
    }
}

