package com.cndll.shapetest.fragment


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.TurnOnActivity
import com.cndll.shapetest.adapter.BannerAdapter
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.HomePageResponse
import com.cndll.shapetest.databinding.*
import com.cndll.shapetest.event.HandlerClick
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [PagerHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagerHomeFragment : BaseVlayoutFragment() {
    var itemTabPosition = 0
    var time = 0
    lateinit var bannerAdapter: BannerAdapter
    //var tablayout: TabLayout? = null
    var homePage: HomePageResponse? = null

    lateinit var shopAdapter: VLayoutAdapter
    lateinit var commodityAdapter: VLayoutAdapter
    lateinit var onSelectListion: TabLayout.OnTabSelectedListener
    lateinit var todaySale: VLayoutAdapter
    lateinit var todaySaleMode: ArrayList<HomePageResponse.DatasBean.TodaySaleBean>

    override fun init() {
        super.init()
        todaySaleMode = ArrayList()
        shopAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(4).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(8).
                setRes(R.layout.item_shop).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter()
        commodityAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(4).
                setLayoutHelper(GridLayoutHelper(2)).
                setViewType(7).
                setRes(R.layout.item_commodity_ver).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
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
                        adapter.removeAdapter(shopAdapter)
                        adapter.addAdapter(2, commodityAdapter)
                    }

                    1 -> {
                        itemTabPosition = 1
                        adapter.removeAdapter(commodityAdapter)
                        adapter.addAdapter(2, shopAdapter)
                    }

                }
            }
        }
        pullData()
    }

    override fun setVLayout() {

        val llh = LinearLayoutHelper()
        bannerAdapter = BannerAdapter(context, llh, 1, layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, windowManager.defaultDisplay.height / 2))
        adapter.addAdapter(bannerAdapter)

        if (arguments.getString("gc_id").equals("1")) {
            val grid = GridLayoutHelper(2, 2)
            grid.setPadding(0, 12, 0, 0)
            grid.setMargin(0, 6, 0, 0)
            adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                    setContext(context).
                    setCount(4).
                    setLayoutHelper(grid).
                    setViewType(2).
                    setRes(R.layout.item_nearby).
                    setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            windowManager.defaultDisplay.height / 13 * 2)).
                    setOnBindView({ itemView, position ->
                        val binding = itemView.dataBinding as ItemNearbyBinding
                        binding.handler = HandlerClick()
                        binding.bundle = Bundle()
                        binding.flag = ""
                        when (position) {
                            (0) -> {
                                binding.flag = NearByShopFoodFragment.FLAG
                            }
                        }
                        binding.image.setOnClickListener { Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show() }
                    }).
                    creatAdapter())
            adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                    setContext(context).
                    setCount(1).
                    setLayoutHelper(LinearLayoutHelper()).
                    setViewType(5).
                    setRes(R.layout.item_checkoutmore).
                    setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            windowManager.defaultDisplay.height / 15)).
                    setOnBindView({ itemView, position ->
                        val binding = itemView.dataBinding as ItemCheckoutmoreBinding
                        // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                        binding.text.text = "查看更多附近 "

                    }).creatAdapter())
            adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                    setContext(context).
                    setCount(1).
                    setLayoutHelper(LinearLayoutHelper()).
                    setViewType(11).
                    setRes(R.layout.item_head).
                    setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            windowManager.defaultDisplay.height / 15)).
                    setOnBindView({ itemView, position ->
                        val binding = itemView.dataBinding as ItemHeadBinding
                        // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                        binding.text.text = "今日特卖 "

                    }).creatAdapter())
            todaySale = object : VLayoutHelper.Builder() {}.
                    setContext(context).
                    setCount(todaySaleMode.size).
                    setLayoutHelper(LinearLayoutHelper()).
                    setViewType(4).
                    setRes(R.layout.item_home_commodity).
                    setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            windowManager.defaultDisplay.height / 3)).
                    setOnBindView({ itemView, position ->
                        val binding = itemView.dataBinding as ItemHomeCommodityBinding
                        if (homePage != null) {
                            binding.image.setImageURI(todaySaleMode[position].image_url)
                            binding.item = todaySaleMode[position]
                            binding.position = position
                            binding.root.setOnClickListener { context.startActivity(Intent(context, TurnOnActivity::class.java).setAction(CommodityInfoFragment.FLAG)) }
                        }
                        // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                    }).creatAdapter()
            adapter.addAdapter(todaySale)
        } else {
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
                            //tablayout = tabBinding.tabLayout
                            tabBinding.tabLayout.getTabAt(itemTabPosition)!!.select()
                            tabBinding.tabLayout.removeOnTabSelectedListener(onSelectListion)
                            tabBinding.tabLayout.addOnTabSelectedListener(onSelectListion)
                        }
                    }).creatAdapter())
            adapter.addAdapter(commodityAdapter)
        }


    }

    override fun onPause() {
        super.onPause()
        //bannerAdapter.view.stopBanner()
    }

    override fun pullData(mode: Int): Boolean {
        Log.e("pullData", arguments.getString("gc_id"))
        if (arguments.getString("gc_id").equals("1")) {
            if (mode == MODE_PULL) {
                page = 1
            }
            ApiUtill.getInstance().getApi(AppRequest.getAPI().homePage(/*"index",*/arguments.getString("gc_id"), page.toString()), {
                baseResponse ->
                when (mode) {
                    (MODE_PULL) -> {
                        homePage = baseResponse as HomePageResponse
                        bannerAdapter.setBanner(homePage!!.datas.carousel)
                        if (todaySaleMode.isNotEmpty()) {
                            todaySaleMode.clear()
                        }
                        todaySaleMode.addAll(baseResponse.datas.today_sale)
                        todaySale.mCount = todaySaleMode.size
                        todaySale.notifyDataSetChanged()
                        Log.e("pull", page.toString())
                    }
                    (MODE_LOADMORE) -> {
                        if ((baseResponse as HomePageResponse).datas.today_sale.isEmpty()) {
                            loadOver()
                        }
                        todaySaleMode.addAll((baseResponse as HomePageResponse).datas.today_sale)
                        todaySale.mCount = todaySaleMode.size
                        todaySale.notifyDataSetChanged()
                        loading = false
                        Log.e("loadmore", page.toString())

                    }
                }
                page++
            })
            return true
        } else {
            return false
        }
    }

    fun pullData() {
        pullData(MODE_PULL)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment PagerHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): PagerHomeFragment {
            val fragment = PagerHomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
