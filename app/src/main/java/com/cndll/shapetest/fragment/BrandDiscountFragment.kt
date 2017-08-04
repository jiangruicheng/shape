package com.cndll.shapetest.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.BrandResponse
import com.cndll.shapetest.databinding.ItemCommodityVerBinding
import com.cndll.shapetest.databinding.ItemLimitTabBinding
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [BrandDiscountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrandDiscountFragment : BaseVlayoutFragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var selectPosition = 0
    var brandMode: ArrayList<BrandResponse.DatasBean> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    lateinit var commodityAdapter: VLayoutAdapter
    override fun init() {
        super.init()
        pullData(MODE_PULL)
        val detailHelper = GridLayoutHelper(2)
        detailHelper.setAutoExpand(false)
        commodityAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(brandMode.size).
                setLayoutHelper(detailHelper).
                setViewType(3).
                setRes(R.layout.item_commodity_ver).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityVerBinding
                    binding.info = brandMode[position]
                    binding.image.setImageURI(brandMode[position].img_url)
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
                    val titles = arrayListOf("默认排序", "销量排序", "价格排序")
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
        adapter.addAdapter(commodityAdapter)
    }

    override fun pullData(mode: Int): Boolean {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().brandPage(),
                {
                    baseResponse ->
                    when (mode) {
                        (MODE_PULL) -> {
                            brandMode = (baseResponse as BrandResponse).datas
                            commodityAdapter.mCount = baseResponse.datas.size
                            commodityAdapter.notifyDataSetChanged()
                        }
                    }
                }
        )
        return true
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        open var FLAG = "品牌折扣"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment BrandDiscountFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): BrandDiscountFragment {
            val fragment = BrandDiscountFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
