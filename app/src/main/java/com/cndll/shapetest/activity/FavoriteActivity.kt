package com.cndll.shapetest.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.MyViewPagerAdapter
import com.cndll.shapetest.databinding.ActivityFavoriteBinding
import com.cndll.shapetest.fragment.FavoriteFragmnet
import com.cndll.shapetest.fragment.MineFragment
import com.cndll.shapetest.fragment.SalesFragment
import com.cndll.shapetest.fragment.TableDataFragment

/**
 * 我的收藏---退换货
 * */
class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {
    private var fragemnts=ArrayList<Fragment>()
    private val titles = arrayOf("商品", "商家")
    private val titlesRe = arrayOf("维权中", "已退款","已拒绝")
    lateinit var type:String
    private var adapter: MyViewPagerAdapter?=null
    override fun initBindingVar() {
    }

    override fun initTitle() {

        binding.titlebar.back.setOnClickListener { finish() }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_favorite)
        initView()
    }

    private fun initView(){
        var bundle=this.intent.extras
        type=bundle.getString("type")
        if(type.equals("collect")){
        binding.titlebar.title.text="我的收藏"
        fragemnts.add(FavoriteFragmnet().newInstance(1))
        fragemnts.add(FavoriteFragmnet().newInstance(2))
        adapter=MyViewPagerAdapter(supportFragmentManager,fragemnts,titles)
        binding.favViewpager.adapter=adapter
        binding.favTab.setupWithViewPager(binding.favViewpager)
        binding.favTab.setTabsFromPagerAdapter(adapter)
        }else if(type.equals("refund")){
            binding.titlebar.title.text="退款管理"
            fragemnts.add(SalesFragment().newInstance(1))
            fragemnts.add(SalesFragment().newInstance(2))
            fragemnts.add(SalesFragment().newInstance(3))
            adapter=MyViewPagerAdapter(supportFragmentManager,fragemnts,titlesRe)
            binding.favViewpager.adapter=adapter
            binding.favTab.setupWithViewPager(binding.favViewpager)
            binding.favTab.setTabsFromPagerAdapter(adapter)
        }

    }

}
