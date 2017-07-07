package com.cndll.shapetest.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityOrdersListBinding
import com.cndll.shapetest.fragment.OrdersFragment

class OrdersListActivity : BaseActivity<ActivityOrdersListBinding>() {
    private val mTabTitles = arrayOfNulls<String>(6)
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text="1545"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_orders_list)
        initView()
    }
    private fun initView(){
        mTabTitles[0] = "全部"
        mTabTitles[1] = "待付款"
        mTabTitles[2] = "待发货"
        mTabTitles[3] = "待收货"
        mTabTitles[4] = "待激励"
        mTabTitles[5] = "待评价"
//        binding.orderTab.setTabMode(TabLayout.MODE_SCROLLABLE)
        binding.tabViewpager.adapter= MorePagerAdapter()
        binding.orderTab.setupWithViewPager(binding.tabViewpager)
    }

    internal inner class MorePagerAdapter : PagerAdapter() {

        override fun getCount(): Int {
            return mTabTitles.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val tv = TextView(this@OrdersListActivity)
            tv.text = "布局2222222222222" + mTabTitles[position]
            tv.textSize = 30.0f
            tv.gravity = Gravity.CENTER
            var sd=OrdersFragment()
            sd.newInstance("pos:"+position)
            container.addView(tv)
            return tv
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mTabTitles[position]+""
        }
    }
}
