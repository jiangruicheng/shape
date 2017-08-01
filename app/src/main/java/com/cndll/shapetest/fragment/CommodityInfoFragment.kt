package com.cndll.shapetest.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.CommodityResponse
import com.cndll.shapetest.databinding.*
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
    var modeCommodity = CommodityResponse.DatasBean()
    var modeGoodsInfo = CommodityResponse.DatasBean.GoodsBean.GoodsInfoBean()
    val adapterlist = ArrayList<DelegateAdapter.Adapter<out RecyclerView.ViewHolder>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun init() {
        super.init()
        ApiUtill.getInstance().getApi(AppRequest.getAPI().commodiytPage(), {
            baseResponse ->
            modeCommodity = (baseResponse as CommodityResponse).datas
            modeGoodsInfo = modeCommodity.goods[0].goods_info
            for (i in adapterlist) {
                i.notifyDataSetChanged()
            }
        })
    }


    override fun setVLayout() {
        super.setVLayout()

        adapterlist.add(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(5).
                setRes(R.layout.item_commodityinfo_banner).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 3)).
                setOnBindView({ itemView, position ->
                    if (modeCommodity.carousel == null) {

                    } else {
                        val bannerBeans = ArrayList<MenuGrid.BannerBean>()
                        for (i in 0..modeCommodity.carousel.size - 1) {
                            bannerBeans.add(MenuGrid.BannerBean(modeCommodity.carousel[i], ""))
                        }
                        val banner = Banner()
                        banner.setBanner(itemView.itemView, bannerBeans)
                        banner.startBanner(1500)
                    }
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val mScrollFixLayoutHelperL = ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_LEFT, 12, 12)
        mScrollFixLayoutHelperL.setItemCount(1)
        mScrollFixLayoutHelperL.showType = ScrollFixLayoutHelper.SHOW_ALWAYS
        adapterlist.add(object : VLayoutHelper.Builder() {}.
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
        adapterlist.add(object : VLayoutHelper.Builder() {}.
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

        adapterlist.add(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(6).
                setRes(R.layout.item_commodityinfo_info).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 7 * 2)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityinfoInfoBinding
                    binding.info = modeCommodity
                    binding.goods = modeGoodsInfo
                }).creatAdapter())
        val guaranteeHelper = GridLayoutHelper(3, 1)
        /*guaranteeHelper.itemCount = 3*/
        adapterlist.add(object : VLayoutHelper.Builder() {}.
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
        adapterlist.add(object : VLayoutHelper.Builder() {}.
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
        adapterlist.add(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(10).
                setRes(R.layout.item_commodityinfo_comment).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                        /*windowManager.defaultDisplay.height / 7 * 2*/)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityinfoCommentBinding
                    binding.info = modeCommodity
                    if (modeCommodity.commtents != null) {
                        binding.comment = modeCommodity.commtents[0]
                        binding.body.avatar.setImageURI(modeCommodity.commtents[0].geavl_memberimg)
                        binding.body.rating.rating = modeCommodity.commtents[0].geval_scores.toFloat()
                    }
                }).creatAdapter())
        val shopHelper = LinearLayoutHelper()
        shopHelper.setMargin(0, 12, 0, 12)
        adapterlist.add(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(shopHelper).
                setViewType(11).
                setRes(R.layout.item_commodityinfo_shop).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 7 * 2)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityinfoShopBinding
                    binding.info = modeCommodity
                    binding.logo.setImageURI(modeCommodity.store_label)
                }).creatAdapter())
        val detailHeadHelper = LinearLayoutHelper()
        detailHeadHelper.setPadding(12, 12, 12, 0)
        adapterlist.add(object : VLayoutHelper.Builder() {}.
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
                }).creatAdapter())
        val detailHelper = GridLayoutHelper(2)
        detailHelper.setAutoExpand(false)
        detailHelper.bgColor = Color.WHITE
        detailHelper.setPadding(12, 12, 12, 24)
        adapterlist.add(object : VLayoutHelper.Builder() {}.
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
        adapterlist.add(object : VLayoutHelper.Builder() {}.
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
        mScrollFixLayoutHelperB.showType = ScrollFixLayoutHelper.SHOW_ALWAYS
        adapterlist.add(object : VLayoutHelper.Builder() {}.
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
        adapter.addAdapters(adapterlist)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        open val FLAG = "商品详情"
        open val TYPE_FIGHT = 0
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
