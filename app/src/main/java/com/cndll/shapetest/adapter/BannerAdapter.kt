package com.cndll.shapetest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.cndll.shapetest.weight.MenuGrid
import java.util.ArrayList

/**
 * Created by jiangruicheng on 2017/7/11.
 */
open class BannerAdapter(context: Context, layoutHelper: LayoutHelper, count: Int) : DelegateAdapter.Adapter<BannerAdapter.BannerViewHolder>() {

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
        val view = MenuGrid(parent)
        val bean1 = MenuGrid.MenuBean()
        bean1.title = "美妆"
        val bean2 = MenuGrid.MenuBean()
        bean2.title = "家电"
        val bean3 = MenuGrid.MenuBean()
        bean3.title = "美女"
        val dataList = listOf<MenuGrid.MenuBean>(bean1, bean2, bean3)
        view.setMenuData(dataList)
        val bannerBeans = ArrayList<MenuGrid.BannerBean>()
        bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
        bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
        bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
        view.setBanner(bannerBeans)
        view.view.layoutParams = mLayoutParams
        return BannerViewHolder(view.view)
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return mLayoutHelper
    }

    inner class BannerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}