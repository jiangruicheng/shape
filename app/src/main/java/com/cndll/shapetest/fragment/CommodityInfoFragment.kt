package com.cndll.shapetest.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ItemCommodityinfoDetailBinding
import com.cndll.shapetest.databinding.ItemCommodityinfoDetailHeadBinding
import com.cndll.shapetest.weight.Banner
import com.cndll.shapetest.weight.MenuGrid
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [CommodityInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommodityInfoFragment : BaseVlayoutFragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun scrollToDo() {
        super.scrollToDo()
/*        if (lastItem == 16) {
            recycler.smoothScrollToPosition(17)
        }*/
    }

    override fun updataRecycle() {
        super.updataRecycle()
    }

    override fun loadMore(): Boolean {
        return super.loadMore()
    }

    override fun setVLayout() {
        super.setVLayout()
/*        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(3).
                setRes(R.layout.item_commodityinfo_title).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 10)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())*/
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(5).
                setRes(R.layout.item_commodityinfo_banner).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 3)).
                setOnBindView({ itemView, position ->
                    val bannerBeans = ArrayList<MenuGrid.BannerBean>()
                    bannerBeans.add(MenuGrid.BannerBean("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg", "http:www.baidu.com"))
                    bannerBeans.add(MenuGrid.BannerBean("http://pic.58pic.com/58pic/15/24/50/43Q58PICkj4_1024.jpg", "http:www.baidu.com"))
                    bannerBeans.add(MenuGrid.BannerBean("http://img0.imgtn.bdimg.com/it/u=3519309645,3088241677&fm=26&gp=0.jpg", "http:www.baidu.com"))
                    val banner = Banner()
                    banner.setBanner(itemView.itemView, bannerBeans)
                    banner.startBanner(1500)
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val mScrollFixLayoutHelperL = ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_LEFT, 12, 12)
        mScrollFixLayoutHelperL.setItemCount(1)
        mScrollFixLayoutHelperL.showType = ScrollFixLayoutHelper.SHOW_ALWAYS
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(mScrollFixLayoutHelperL).
                setViewType(3).
                setRes(R.layout.item_commodityinfo_topbutton).
                setParams(ViewGroup.LayoutParams(windowManager.defaultDisplay.height / 14, windowManager.defaultDisplay.height / 14)).
                setOnBindView({ itemView, position ->
                    val button = itemView.itemView.findViewById(R.id.top_button) as ImageView
                    button.setImageResource(R.mipmap.back_info)
                    button.setOnClickListener { activity.finish() }
                }).creatAdapter())
        val mScrollFixLayoutHelperR = ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT, 12, 12)
        mScrollFixLayoutHelperR.setItemCount(1)
        mScrollFixLayoutHelperR.showType = ScrollFixLayoutHelper.SHOW_ALWAYS
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(mScrollFixLayoutHelperR).
                setViewType(4).
                setRes(R.layout.item_commodityinfo_topbutton).
                setParams(ViewGroup.LayoutParams(windowManager.defaultDisplay.height / 14, windowManager.defaultDisplay.height / 14)).
                setOnBindView({ itemView, position ->
                    val button = itemView.itemView.findViewById(R.id.top_button) as ImageView
                    button.setImageResource(R.mipmap.share)
                }).creatAdapter())

        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(6).
                setRes(R.layout.item_commodityinfo_info).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 7 * 2)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val guaranteeHelper = GridLayoutHelper(3, 1)
        /*guaranteeHelper.itemCount = 3*/
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(3).
                setLayoutHelper(guaranteeHelper).
                setViewType(7).
                setRes(R.layout.item_commdityinfo_guarantee).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 15)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val itemCommodityInfoChooseHelper = LinearLayoutHelper()
        itemCommodityInfoChooseHelper.setMargin(0, 12, 0, 12)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(itemCommodityInfoChooseHelper).
                setViewType(9).
                setRes(R.layout.item_commodityinfo_choose).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 15)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(10).
                setRes(R.layout.item_commodityinfo_comment).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                        /*windowManager.defaultDisplay.height / 7 * 2*/)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val shopHelper = LinearLayoutHelper()
        shopHelper.setMargin(0, 12, 0, 12)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(shopHelper).
                setViewType(11).
                setRes(R.layout.item_commodityinfo_shop).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 7 * 2)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val detailHeadHelper = LinearLayoutHelper()
        detailHeadHelper.setPadding(12, 12, 12, 0)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(detailHeadHelper).
                setViewType(12).
                setRes(R.layout.item_commodityinfo_detail_head).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                        /*windowManager.defaultDisplay.height / 7 * 2*/)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityinfoDetailHeadBinding
                    binding.text.text = "商品详情"
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val detailHelper = GridLayoutHelper(2)
        detailHelper.setAutoExpand(false)
        detailHelper.bgColor = Color.WHITE
        detailHelper.setPadding(12, 12, 12, 24)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(5).
                setLayoutHelper(detailHelper).
                setViewType(13).
                setRes(R.layout.item_commodityinfo_detail).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                        /*windowManager.defaultDisplay.height / 7 * 2*/)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityinfoDetailBinding
                    binding.text.gravity = Gravity.CENTER_VERTICAL
                    binding.text.text = "厂商：保时捷"
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(8).
                setRes(R.layout.item_webview).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)).
                setOnBindView({ itemView, position ->
                    val web = itemView.itemView.findViewById(R.id.web) as WebView
                    web.setWebViewClient(object : WebViewClient() {})
                    web.loadUrl("http://www.baidu.com")
                    web.settings.javaScriptCanOpenWindowsAutomatically = true
                    web.settings.javaScriptEnabled = true
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val mScrollFixLayoutHelperB = ScrollFixLayoutHelper(ScrollFixLayoutHelper.BOTTOM_RIGHT, 0, 0)
        mScrollFixLayoutHelperL.showType = ScrollFixLayoutHelper.SHOW_ALWAYS
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(mScrollFixLayoutHelperB).
                setViewType(14).
                setRes(R.layout.item_commodityinfo_bottom).
                setParams(ViewGroup.LayoutParams(windowManager.defaultDisplay.width,
                        windowManager.defaultDisplay.height / 14)).
                setOnBindView({ itemView, position ->

                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        open val FLAG = "商品详情"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment CommodityInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): CommodityInfoFragment {
            val fragment = CommodityInfoFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
