package com.cndll.shapetest.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
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
import com.cndll.shapetest.databinding.ItemCommodityVerBinding
import com.cndll.shapetest.databinding.ItemLimitTabBinding
import com.cndll.shapetest.weight.VLayoutHelper


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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    lateinit var commodityAdapter: VLayoutAdapter
    lateinit var shopAdapter: VLayoutAdapter
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
        val detailHelper = GridLayoutHelper(2)
        detailHelper.setAutoExpand(false)
        commodityAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(12).
                setLayoutHelper(detailHelper).
                setViewType(3).
                setRes(R.layout.item_commodity_ver).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityVerBinding

                }).creatAdapter()
    }

    override fun scrollToDo() {
        super.scrollToDo()
    }

    override fun loadOver() {
        super.loadOver()
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
        when (arguments.getInt(ResultActivity.TYPE)) {
            (ResultActivity.SHOP) -> {
                adapter.addAdapter(shopAdapter)
            }
            (ResultActivity.COMMODIYT) -> {
                adapter.addAdapter(commodityAdapter)
            }
        }
    }

    override fun pullData(mode: Int): Boolean {
        return super.pullData(mode)
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
