package com.cndll.shapetest.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.MyViewPagerAdapter
import com.cndll.shapetest.databinding.ActivityPopularizeBinding
import com.cndll.shapetest.fragment.PopularizeFragment

/**
 * 我的推广
 * */
class PopularizeActivity : BaseActivity<ActivityPopularizeBinding>() {
    private var fragemnts=ArrayList<Fragment>()
    private val titles = arrayOf("用户推广", "与我关联")
    private var adapter: MyViewPagerAdapter?=null
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="我的推广"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_popularize)
        initView()
    }

    private fun initView(){
        fragemnts.add(PopularizeFragment().newInstance(1))
        fragemnts.add(PopularizeFragment().newInstance(2))
        adapter=MyViewPagerAdapter(supportFragmentManager,fragemnts,titles)
        binding.popularViewpager.adapter=adapter
        binding.popularTab.setupWithViewPager(binding.popularViewpager)
        binding.popularTab.setTabsFromPagerAdapter(adapter)

    }

}
