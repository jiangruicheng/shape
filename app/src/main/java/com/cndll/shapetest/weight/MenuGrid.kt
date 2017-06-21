package com.cndll.shapetest.weight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cndll.shapetest.R
import kotlin.concurrent.thread

/**
 * Created by kongqing on 2017/6/20.
 */
class MenuGrid(view: ViewGroup?, dataList: List<MenuBean>?) {
    var view: View = LayoutInflater.from(view?.context).inflate(R.layout.banner, view, false)
    fun setViewData() {
        thread {
            val menu = view.findViewById(R.id.menu) as ViewGroup
            for (i in 0..menu.childCount - 1) {
                val cmenu = menu.getChildAt(i) as ViewGroup
                for (k in 0..cmenu.childCount - 1) {
                    val ccmenu = cmenu.getChildAt(k) as ViewGroup
                    for (j in 0..ccmenu.childCount - 1) {
                        val v = ccmenu.getChildAt(j)
                        if (v is ImageView) {
                            //val uri = Uri.parse("http://img0.imgtn.bdimg.com/it/u=3884162342,2808468014&fm=26&gp=0.jpg")
                            v.post { (v as ImageView).setImageResource(R.mipmap.chat) }
                            // (ccmenu.getChildAt(k) as ImageView).setImageURI(uri)
                        } else {
                            v.post { (v as TextView).text = "首页" }
                        }
                    }
                }
            }
        }
    }

    open class MenuBean {
        lateinit var title: String
        lateinit var imageUrl: String
    }
}