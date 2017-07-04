package com.cndll.shapetest.weight

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bigkoo.convenientbanner.holder.Holder
import com.cndll.shapetest.R
import com.facebook.drawee.view.SimpleDraweeView
import kotlin.concurrent.thread

/**
 * Created by kongqing on 2017/6/20.
 */
class MenuGrid(view: ViewGroup?) {
    var view: View = LayoutInflater.from(view?.context).inflate(R.layout.banner, view, false)

    fun setBanner(dataList: List<BannerBean>) {
        val banner = Banner()
        banner.setBanner(view, dataList)
    }

    fun setMenuData(dataList: List<MenuBean>?) {
        thread {
            val menu = view.findViewById(R.id.menu) as ViewGroup
            for (i in 0..menu.childCount - 1) {
                val cmenu = menu.getChildAt(i) as ViewGroup
                for (k in 0..cmenu.childCount - 1) {
                    val ccmenu = cmenu.getChildAt(k) as ViewGroup
                    for (j in 0..ccmenu.childCount - 1) {
                        val v = ccmenu.getChildAt(j)
                        if (v is ImageView) {

                            v.setOnClickListener(View.OnClickListener { Toast.makeText(v.context, v.toString(), Toast.LENGTH_SHORT).show() })
                            v.post {

                                if (dataList != null && k < dataList.size && dataList.get(k).imageUrl != "") {
                                    v.setImageURI(Uri.parse(dataList.get(k).imageUrl))
                                } else {
                                    v.setImageResource(R.mipmap.chat)
                                }
                            }
                        } else {
                            v.post {
                                if (dataList != null && k < dataList.size && dataList.get(k).title != "") {
                                    (v as TextView).text = dataList.get(k).title
                                } else {
                                    (v as TextView).text = "首页"
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    open class BannerBean(imageUrl: String, gotoUrl: String) {
        var imageUrl: String = ""
        var gotoUrl: String = ""

        init {
            this.imageUrl = imageUrl
            this.gotoUrl = gotoUrl
        }
    }

    open class MenuBean {
        var title: String = ""
        var imageUrl: String = ""
    }

    open class LocalImageHolderView : Holder<String> {
        private lateinit var imageView: SimpleDraweeView
        override fun createView(context: Context?): View {
            imageView = SimpleDraweeView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            return imageView
        }

        override fun UpdateUI(context: Context?, position: Int, data: String?) {
            imageView.setImageURI(data)
        }

    }
}