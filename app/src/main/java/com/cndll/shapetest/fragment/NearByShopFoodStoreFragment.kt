package com.cndll.shapetest.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.databinding.ItemTablayoutBinding
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [NearByShopFoodStoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NearByShopFoodStoreFragment : BaseVlayoutFragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    lateinit var onSelectListion: TabLayout.OnTabSelectedListener
    lateinit var webview: WebView
    var itemTabPosition = 0
    lateinit var shoppingCartAdapter: VLayoutAdapter
    lateinit var shopInfoAdapter: VLayoutAdapter
    lateinit var commodityAdapter: VLayoutAdapter
    override fun init() {
        super.init()
        // addItemDecoration()
        webview = WebView(context)
        webview.loadUrl("http://www.baidu.com")
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.settings.javaScriptEnabled = true
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        webview.layoutParams = params
        webview.setWebViewClient(object : WebViewClient() {})

        val mScrollFixLayoutHelperB = ScrollFixLayoutHelper(ScrollFixLayoutHelper.BOTTOM_RIGHT, 0, 0)
        mScrollFixLayoutHelperB.showType = ScrollFixLayoutHelper.SHOW_ALWAYS
        shoppingCartAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(mScrollFixLayoutHelperB).
                setViewType(14).
                setRes(R.layout.item_shoppingcart).
                setParams(ViewGroup.LayoutParams(windowManager.defaultDisplay.width,
                        windowManager.defaultDisplay.height / 10)).
                setOnBindView({ itemView, position ->

                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter()

        commodityAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(9).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(4).
                setRes(R.layout.item_nearby_commodity).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 15 * 2)).
                setOnBindView({ itemView, position ->

                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter()

        shopInfoAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(7).
                setRes(R.layout.item_nearby_shopinfo).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)).
                setOnBindView({ itemView, position ->
                    if (webview.parent != null) {
                        (webview.parent as ViewGroup).removeView(webview)
                    }
                    (itemView.itemView as ViewGroup).addView(webview)
                    /*val webview = itemView.itemView.findViewById(R.id.web) as WebView
                    webview.loadUrl("http://www.baidu.com")
                    webview.settings.javaScriptCanOpenWindowsAutomatically = true
                    webview.settings.javaScriptEnabled = true
                    webview.setWebViewClient(object : WebViewClient() {})*/
                }).creatAdapter()


        onSelectListion = object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        itemTabPosition = 0
                        adapter.removeAdapter(3)
                        adapter.addAdapter(3, commodityAdapter)
                        adapter.addAdapter(4, shoppingCartAdapter)
                    }

                    1 -> {
                        itemTabPosition = 1
                        adapter.removeAdapter(3)
                        adapter.removeAdapter(3)
                        adapter.addAdapter(3, shopInfoAdapter)
                    }

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun setVLayout() {
        super.setVLayout()
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(3).
                setRes(R.layout.item_nearby_food).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 11 * 2)).
                setOnBindView({ itemView, position ->

                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(3).
                setRes(R.layout.item_nearby_location).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 15)).
                setOnBindView({ itemView, position ->

                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(6).
                setRes(R.layout.item_tablayout).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 15)).
                setOnBindView({ itemView, position ->
                    if (itemView.dataBinding != null) {
                        val tabBinding = itemView.dataBinding as ItemTablayoutBinding
                        tabBinding.tabLayout.getTabAt(itemTabPosition)!!.select()
                        tabBinding.tabLayout.removeOnTabSelectedListener(onSelectListion)
                        tabBinding.tabLayout.addOnTabSelectedListener(onSelectListion)
                    }
                }).creatAdapter())
        adapter.addAdapter(commodityAdapter)

        adapter.addAdapter(shoppingCartAdapter)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        public val ARG_PARAM1 = "param1"
        public val ARG_PARAM2 = "param2"
        open var FLAG = "商家店铺"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment NearByShopFoodStoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): NearByShopFoodStoreFragment {
            val fragment = NearByShopFoodStoreFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
