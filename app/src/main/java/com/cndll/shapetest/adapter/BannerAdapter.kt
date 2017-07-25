package com.cndll.shapetest.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.LayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.TurnOnActivity
import com.cndll.shapetest.fragment.*
import com.cndll.shapetest.tools.StringTools
import com.cndll.shapetest.weight.MenuGrid
import kotlin.collections.ArrayList

/**
 * Created by jiangruicheng on 2017/7/11.
 */
open class BannerAdapter(context: Context, layoutHelper: LayoutHelper, count: Int) : VLayoutAdapter(context, layoutHelper, count) {
    lateinit var view: MenuGrid
    lateinit var meunBeans: ArrayList<MenuGrid.MenuBean>
    lateinit var bannerBeas: ArrayList<MenuGrid.BannerBean>

    constructor(context: Context, layoutHelper: LayoutHelper, count: Int, layoutParams: ViewGroup.LayoutParams) : this(context, layoutHelper, count) {
        mLayoutParams = layoutParams
    }

    override fun onBindViewHolder(holder: BannerViewHolder?, position: Int) {
    }

    override fun getItemViewType(position: Int): Int {
        return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        view = MenuGrid(parent)
        val bean1 = MenuGrid.MenuBean()
        bean1.title = "限时秒杀"
        bean1.imageUrl = StringTools.getResUri(R.mipmap.miaosha, mContext)
        bean1.onclick = View.OnClickListener { mContext.startActivity(Intent(mContext, TurnOnActivity::class.java).setAction(LimitedSpikeFragment.FLAG)) }
        val bean2 = MenuGrid.MenuBean()
        bean2.title = "品牌折扣"
        bean2.imageUrl = StringTools.getResUri(R.mipmap.zhekou, mContext)
        bean2.onclick = View.OnClickListener { mContext.startActivity(Intent(mContext, TurnOnActivity::class.java).setAction(CommodityInfoFragment.FLAG)) }
        val bean3 = MenuGrid.MenuBean()
        bean3.title = "亲友拼团"
        bean3.imageUrl = StringTools.getResUri(R.mipmap.pintuan, mContext)
        bean3.onclick = View.OnClickListener { mContext.startActivity(Intent(mContext, TurnOnActivity::class.java).setAction(MissionToFightFragment.FLAG)) }
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
        bean7.onclick = View.OnClickListener { mContext.startActivity(Intent(mContext, TurnOnActivity::class.java).setAction(NearByShopFoodFragment.FLAG)) }

        val bean8 = MenuGrid.MenuBean()
        bean8.title = "线下体验"
        bean8.imageUrl = StringTools.getResUri(R.mipmap.tiyandian, mContext)
        bean8.onclick = View.OnClickListener { mContext.startActivity(Intent(mContext, TurnOnActivity::class.java).setAction(LineaOffShopFragment.FLAG)) }

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

}