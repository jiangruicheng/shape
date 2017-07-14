package com.cndll.shapetest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.tools.StringTools
import com.cndll.shapetest.weight.MenuGrid
import java.util.ArrayList

/**
 * Created by jiangruicheng on 2017/7/11.
 */
open class BannerAdapter(context: Context, layoutHelper: LayoutHelper, count: Int) : DelegateAdapter.Adapter<BannerAdapter.BannerViewHolder>() {
    lateinit var view: MenuGrid

    constructor(context: Context, layoutHelper: LayoutHelper, count: Int, layoutParams: ViewGroup.LayoutParams) : this(context, layoutHelper, count) {
        mLayoutParams = layoutParams
    }

    lateinit var mContext: Context
    lateinit var mLayoutHelper: LayoutHelper
    var mCount = 0
    var mLayoutParams: ViewGroup.LayoutParams? = null

    init {
        mContext = context
        mLayoutHelper = layoutHelper
        mCount = count

        if (mLayoutParams == null) {
            val windowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val height = windowManager.defaultDisplay.height / 2
            mLayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
        }

    }

    override fun onBindViewHolder(holder: BannerViewHolder?, position: Int) {
    }

    override fun getItemCount(): Int {
        return mCount
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        view = MenuGrid(parent)
        val bean1 = MenuGrid.MenuBean()
        bean1.title = "限时秒杀"
        bean1.imageUrl = StringTools.getResUri(R.mipmap.miaosha, mContext)
        val bean2 = MenuGrid.MenuBean()
        bean2.title = "品牌折扣"
        bean2.imageUrl = StringTools.getResUri(R.mipmap.zhekou, mContext)
        val bean3 = MenuGrid.MenuBean()
        bean3.title = "亲友拼团"
        bean3.imageUrl = StringTools.getResUri(R.mipmap.pintuan, mContext)
        val bean4 = MenuGrid.MenuBean()
        bean4.title = "会员特权"
        bean4.imageUrl = StringTools.getResUri(R.mipmap.huiyuan, mContext)
        val bean5 = MenuGrid.MenuBean()
        bean5.title = "服饰箱包"
        bean5.imageUrl = StringTools.getResUri(R.mipmap.fushi, mContext)
        val bean6 = MenuGrid.MenuBean()
        bean6.title = "进口海购"
        bean6.imageUrl = StringTools.getResUri(R.mipmap.jkinkou, mContext)
        val bean7 = MenuGrid.MenuBean()
        bean7.title = "积分专区"
        bean7.imageUrl = StringTools.getResUri(R.mipmap.jifen, mContext)
        val bean8 = MenuGrid.MenuBean()
        bean8.title = "线下体验"
        bean8.imageUrl = StringTools.getResUri(R.mipmap.tiyandian, mContext)

        val dataList = listOf<MenuGrid.MenuBean>(bean1, bean2, bean3, bean4, bean5, bean6, bean7, bean8)
        view.setMenuData(dataList)
        val bannerBeans = ArrayList<MenuGrid.BannerBean>()
        bannerBeans.add(MenuGrid.BannerBean("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg", "http:www.baidu.com"))
        bannerBeans.add(MenuGrid.BannerBean("http://pic.58pic.com/58pic/15/24/50/43Q58PICkj4_1024.jpg", "http:www.baidu.com"))
        bannerBeans.add(MenuGrid.BannerBean("http://img0.imgtn.bdimg.com/it/u=3519309645,3088241677&fm=26&gp=0.jpg", "http:www.baidu.com"))
        view.view.layoutParams.height = mLayoutParams!!.height
        view.view.layoutParams.width = mLayoutParams!!.width
        view.setBanner(bannerBeans)
        view.startBanner(3500)
        return BannerViewHolder(view.view)
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return mLayoutHelper
    }

    inner class BannerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}