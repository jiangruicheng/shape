package com.cndll.shapetest.fragment


import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.BannerAdapter
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.databinding.ItemCheckoutmoreBinding
import com.cndll.shapetest.databinding.ItemHeadBinding
import com.cndll.shapetest.databinding.ItemNearbyBinding
import com.cndll.shapetest.databinding.ItemTablayoutBinding
import com.cndll.shapetest.weight.CountdownTextView
import com.cndll.shapetest.weight.VLayoutHelper
import kotlin.concurrent.thread


/**
 * A simple [Fragment] subclass.
 * Use the [PagerHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagerHomeFragment : BaseVlayoutFragment() {
    var isHomePage = true
    var itemTabPosition = 0
    var time = 0
    lateinit var bannerAdapter: BannerAdapter
    var tablayout: TabLayout? = null


    lateinit var shopAdapter: VLayoutAdapter
    lateinit var commodityAdapter: VLayoutAdapter
    lateinit var onSelectListion: TabLayout.OnTabSelectedListener
    override fun init() {
        super.init()

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
                        adapter.removeAdapter(2)
                        adapter.addAdapter(2, commodityAdapter)
                    }

                    1 -> {
                        itemTabPosition = 1
                        adapter.removeAdapter(2)
                        adapter.addAdapter(2, shopAdapter)
                    }

                }
            }
        }
    }

    override fun setVLayout() {


        val llh = LinearLayoutHelper()
        bannerAdapter = BannerAdapter(context, llh, 1, layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, windowManager.defaultDisplay.height / 2))
        adapter.addAdapter(bannerAdapter)

        if (isHomePage) {
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
                        // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                        binding.image.setOnClickListener { Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show() }
                    }).creatAdapter())
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
            adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                    setContext(context).
                    setCount(4).
                    setLayoutHelper(LinearLayoutHelper()).
                    setViewType(4).
                    setRes(R.layout.item_home_commodity).
                    setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            windowManager.defaultDisplay.height / 3)).
                    setOnBindView({ itemView, position ->
                        // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                    }).creatAdapter())
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
                            tablayout = tabBinding.tabLayout
                            tabBinding.tabLayout.getTabAt(itemTabPosition)!!.select()
                            tabBinding.tabLayout.removeOnTabSelectedListener(onSelectListion)
                            tabBinding.tabLayout.addOnTabSelectedListener(onSelectListion)

                        }
                    }).creatAdapter())
            adapter.addAdapter(commodityAdapter)
        }


    }


    override fun loadMore(): Boolean {
        return super.loadMore()
        /*if (!canLoad) {
            return true
        }
        Log.d("COUNT", (adapter.findAdapterByIndex(3) as VLayoutAdapter).mCount.toString())
        if ((adapter.findAdapterByIndex(3) as VLayoutAdapter).mCount >= 12) {

            return false
        }
        (adapter.findAdapterByIndex(3) as VLayoutAdapter).mCount = (adapter.findAdapterByIndex(3) as VLayoutAdapter).mCount + 3
        (adapter.findAdapterByIndex(3) as VLayoutAdapter).notifyDataSetChanged()
        return true*/
    }

    override fun onPause() {
        super.onPause()
        //bannerAdapter.view.stopBanner()
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
