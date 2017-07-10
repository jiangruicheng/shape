package com.cndll.shapetest.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.MyViewPagerAdapter
import com.cndll.shapetest.databinding.ActivityOrdersListBinding
import com.cndll.shapetest.fragment.MineFragment
import com.cndll.shapetest.fragment.OrdersFragment
import com.cndll.shapetest.fragment.TableDataFragment

/**
 * 我的订房
 * */
class OrdersListActivity : BaseActivity<ActivityOrdersListBinding>() {

    private var fragemnts=ArrayList<Fragment>()
    private val titles = arrayOf("全部", "待付款","待发货","待收货","待激励","待评价")
    private var adapter: MyViewPagerAdapter?=null
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text="我的订单"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_orders_list)
        initView()
    }
    private fun initView(){
        fragemnts.add(OrdersFragment().newInstance(1))
        fragemnts.add(OrdersFragment().newInstance(2))
        fragemnts.add(OrdersFragment().newInstance(3))
        fragemnts.add(OrdersFragment().newInstance(4))
        fragemnts.add(OrdersFragment().newInstance(5))
        fragemnts.add(OrdersFragment().newInstance(6))
        adapter=MyViewPagerAdapter(supportFragmentManager,fragemnts,titles)
        binding.tabViewpager.adapter=adapter
        binding.orderTab.setupWithViewPager(binding.tabViewpager)
        binding.orderTab.setTabsFromPagerAdapter(adapter)
    }
}
