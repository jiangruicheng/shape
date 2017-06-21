package com.cndll.shapetest.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityHomeBinding
import com.cndll.shapetest.fragment.HomeFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun initTitle() {
        binding.titlebar.root.setBackgroundResource(R.color.titleRed)
        binding.titlebar.title.text = "众享消费"
        binding.titlebar.title.setTextColor(Color.WHITE)
        binding.titlebar.back.visibility = View.GONE
        binding.titlebar.menu.visibility = View.GONE
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_home)
        initBottom()
        supportFragmentManager.beginTransaction().add(R.id.frame, HomeFragment.newInstance("", "")).commit()
    }

    fun initBottom() {
        binding.bottom.setMode(BottomNavigationBar.MODE_FIXED)
        binding.bottom.setActiveColor(R.color.titleRed).setBarBackgroundColor(R.color.white)
        binding.bottom.addItem(BottomNavigationItem(R.mipmap.home, "首页")).
                addItem(BottomNavigationItem(R.mipmap.search, "搜索")).
                addItem(BottomNavigationItem(R.mipmap.data, "数据")).
                addItem(BottomNavigationItem(R.mipmap.chat, "社交")).
                addItem(BottomNavigationItem(R.mipmap.person, "我的")).initialise()

    }

    fun addFragment(frame: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(frame, fragment)

    }

}
