package com.cndll.shapetest.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.ResultActivity
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.ClassCommodity
import com.cndll.shapetest.api.bean.response.ClassShop
import com.cndll.shapetest.api.bean.response.SearchShopResponse
import com.cndll.shapetest.api.bean.response.SearcheCommodityResponse
import com.cndll.shapetest.bean.CommodityVerInfoMode
import com.cndll.shapetest.bean.ShopInfoMode
import com.cndll.shapetest.bean.anno.Voluation
import com.cndll.shapetest.databinding.ItemCommodityVerBinding
import com.cndll.shapetest.databinding.ItemLimitTabBinding
import com.cndll.shapetest.databinding.ItemShopBinding
import com.cndll.shapetest.weight.VLayoutHelper
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.popview_switch_commodityandshop.*


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : BaseVlayoutFragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var selectPosition = 0
    var type = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    val shopMode = ArrayList<ShopInfoMode>()
    val commodityMode = ArrayList<CommodityVerInfoMode>()
    lateinit var commodityAdapter: VLayoutAdapter
    lateinit var shopAdapter: VLayoutAdapter
    override fun init() {
        super.init()
        type = arguments.getInt(ResultActivity.TYPE)
        pullData(MODE_PULL)
        shopAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(shopMode.size).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(8).
                setRes(R.layout.item_shop).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemShopBinding
                    binding.info = shopMode[position]
                    binding.logo.setImageURI(shopMode[position].logoUrl)
                    for (i in 0..shopMode[position].urls.size - 1) {
                        if (i > binding.commodity.childCount - 1) {
                            break
                        }
                        (binding.commodity.getChildAt(i) as SimpleDraweeView).setImageURI(shopMode[position].urls[i].url)
                    }
                }).creatAdapter()
        val detailHelper = GridLayoutHelper(2)
        detailHelper.setAutoExpand(false)
        commodityAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(commodityMode.size).
                setLayoutHelper(detailHelper).
                setViewType(3).
                setRes(R.layout.item_commodity_ver).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityVerBinding
                    binding.info = commodityMode[position]
                    binding.image.setImageURI(commodityMode[position].imgUrl)
                }).creatAdapter()
    }


    override fun setVLayout() {
        super.setVLayout()
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(2).
                setRes(R.layout.item_limited_tablayout).
                setParams(ViewGroup.LayoutParams(windowManager.defaultDisplay.width,
                        windowManager.defaultDisplay.height / 15)).
                setOnBindView({ itemView, position ->
                    val tabLayout: TabLayout = itemView.itemView.findViewById(R.id.tab) as TabLayout
                    tabLayout.tabMode = TabLayout.MODE_FIXED
                    tabLayout.tabGravity = TabLayout.GRAVITY_FILL
                    val p = selectPosition
                    tabLayout.removeAllTabs()
                    val titles = arrayListOf("默认排序", "销量排序", "好评优先")
                    for (i in 0..2) {
                        val tab: TabLayout.Tab = tabLayout.newTab()
                        val tabView = LayoutInflater.from(context).inflate(R.layout.item_limit_tab, tabLayout as ViewGroup, false)
                        val tabViewBinding = ItemLimitTabBinding.bind(tabView)
                        tab.setCustomView(tabView)
                        (tabViewBinding.group?.parent as View).setBackgroundColor(Color.WHITE)
                        tabViewBinding.group.setBackgroundColor(Color.WHITE)
                        tabViewBinding.time.text = titles[i]
                        tabViewBinding.status.visibility = View.GONE
                        tabLayout.addTab(tab)
                        if (i == p) {
                            tabViewBinding.time.setTextColor(Color.rgb(0xf0, 0x39, 0x3c))
                        } else {
                            tabViewBinding.time.setTextColor(Color.BLACK)
                        }
                    }
                    Toast.makeText(context, p.toString(), Toast.LENGTH_SHORT).show()
                    tabLayout.getTabAt(p)?.select()
                    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabReselected(tab: TabLayout.Tab?) {
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {
                            val time = tab!!.customView?.findViewById(R.id.time) as TextView
                            time.setTextColor(Color.BLACK)
                        }

                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            selectPosition = tab!!.position
                            val time = tab.customView?.findViewById(R.id.time) as TextView
                            time.setTextColor(Color.rgb(0xf0, 0x39, 0x3c))
                        }
                    })
                }).creatAdapter())
        when (arguments.getInt(ResultActivity.TYPE)) {
            (ResultActivity.TYPE_SHOP) -> {
                adapter.addAdapter(shopAdapter)
            }
            (ResultActivity.TYPE_COMMODIYT) -> {
                adapter.addAdapter(commodityAdapter)
            }
        }
    }

    override fun pullData(mode: Int): Boolean {
        when (arguments.getInt(ResultActivity.TYPE)) {
            (ResultActivity.TYPE_COMMODIYT) -> {
                getCommodity()
            }
            (ResultActivity.TYPE_SHOP) -> {
                getShop()
            }
        }
        return true
    }

    fun getShop() {
        when (arguments.getInt(ResultActivity.MODE)) {
            (ResultActivity.MODE_CLASS) -> {
                Log.e("id", arguments.getString(ID))
                ApiUtill.getInstance().getApi(AppRequest.getAPI().classShopPage(arguments.getString(ID), page.toString(), ResultActivity.SHOP_ORDER_DEFAULT), {
                    baseResponse ->
                    baseResponse as ClassShop
                    if (shopMode.isNotEmpty()) {
                        shopMode.clear()
                    }
                    val v = Voluation<ClassShop.DatasBean, ShopInfoMode>()
                    for (i in baseResponse.datas) {
                        val s = v.getVari(i, ShopInfoMode::class.java)
                        for (a in i.goods) {
                            val goods = ShopInfoMode.Goods()
                            goods.id = a.goods_id
                            goods.url = a.img_url
                            if (s.urls == null) {
                                s.urls = ArrayList<ShopInfoMode.Goods>()
                            }
                            s.urls.add(goods)
                        }
                        shopMode.add(s)
                    }
                    shopAdapter.mCount = shopMode.size
                    shopAdapter.notifyDataSetChanged()
                })
            }
            (ResultActivity.MODE_SEARCH) -> {
                ApiUtill.getInstance().getApi(AppRequest.getAPI().searchShopPage(arguments.getString(ResultActivity.SEARCHKE), page.toString()), {
                    baseResponse ->
                    baseResponse as SearchShopResponse
                    if (shopMode.isNotEmpty()) {
                        shopMode.clear()
                    }
                    val v = Voluation<SearchShopResponse.DatasBean.StoreBean, ShopInfoMode>()
                    for (i in baseResponse.datas.store) {
                        val s = v.getVari(i, ShopInfoMode::class.java)
                        for (a in i.goods) {
                            val goods = ShopInfoMode.Goods()
                            goods.id = a.goods_id
                            goods.url = a.img_url
                            if (s.urls == null) {
                                s.urls = ArrayList<ShopInfoMode.Goods>()
                            }
                            s.urls.add(goods)
                        }
                        shopMode.add(s)
                    }
                    shopAdapter.mCount = shopMode.size
                    shopAdapter.notifyDataSetChanged()
                })
            }
        }
    }

    fun getCommodity() {
        when (arguments.getInt(ResultActivity.MODE)) {
            (ResultActivity.MODE_CLASS) -> {
                Log.e("id", arguments.getString(ID))
                ApiUtill.getInstance().getApi(AppRequest.getAPI().classCommotidyPage(arguments.getString(ID), page.toString(), ResultActivity.GOODS_ORDER_DEFAULT), {
                    baseResponse ->
                    baseResponse as ClassCommodity
                    if (commodityMode.isNotEmpty()) {
                        commodityMode.clear()
                    }
                    for (i in baseResponse.datas) {
                        val v = Voluation<ClassCommodity.DatasBean, CommodityVerInfoMode>()
                        commodityMode.add(v.getVari(i, CommodityVerInfoMode::class.java))
                    }
                    commodityAdapter.mCount = commodityMode.size
                    commodityAdapter.notifyDataSetChanged()
                })
            }
            (ResultActivity.MODE_SEARCH) -> {
                Log.e("SEARCHKE", arguments.getString(ResultActivity.SEARCHKE, "123"))

                ApiUtill.getInstance().getApi(AppRequest.getAPI().searchCommodityPage(arguments.getString(ResultActivity.SEARCHKE), page.toString()), {
                    baseResponse ->
                    baseResponse as SearcheCommodityResponse
                    if (commodityMode.isNotEmpty()) {
                        commodityMode.clear()
                    }
                    for (i in baseResponse.datas.goods) {
                        val v = Voluation<SearcheCommodityResponse.DatasBean.GoodsBean, CommodityVerInfoMode>()
                        commodityMode.add(v.getVari(i, CommodityVerInfoMode::class.java))
                    }
                    commodityAdapter.mCount = commodityMode.size
                    commodityAdapter.notifyDataSetChanged()
                })
            }
        }
    }

     fun switchType(mtype: Int) {
        when (mtype) {
            (ResultActivity.TYPE_SHOP) -> {
                if (type != mtype) {
                    adapter.removeAdapter(commodityAdapter)
                    adapter.addAdapter(shopAdapter)
                    type = mtype
                    getShop()
                }
            }
            (ResultActivity.TYPE_COMMODIYT) -> {
                if (type != mtype) {
                    adapter.removeAdapter(shopAdapter)
                    adapter.addAdapter(commodityAdapter)
                    type = mtype
                    getCommodity()
                }
            }
        }
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        val ID = "gc_id"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
