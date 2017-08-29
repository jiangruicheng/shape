package com.cndll.shapetest.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.widget.Toast
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityHomeBinding
import com.cndll.shapetest.fragment.ClassificationFragment
import com.cndll.shapetest.fragment.HomeFragment
import com.cndll.shapetest.fragment.MineFragment
import com.cndll.shapetest.fragment.TableDataFragment
import com.cndll.shapetest.tools.AppManager
import com.cndll.shapetest.tools.XPermissionUtils
import kotlin.concurrent.thread

class HomeActivity : BaseActivity<ActivityHomeBinding>(), BottomNavigationBar.OnTabSelectedListener {

    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {
    }

    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment.newInstance("", "")).commit()
            1 -> supportFragmentManager.beginTransaction().replace(R.id.frame, ClassificationFragment.newInstance("", "")).commit()

            2 -> supportFragmentManager.beginTransaction().replace(R.id.frame, TableDataFragment.newInstance("", "")).commit()
            4 -> supportFragmentManager.beginTransaction().replace(R.id.frame, MineFragment.newInstance("", "")).commit()
        }

    }

    override fun initTitle() {
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_home)
        initBottom()
    }

    fun initBottom() {
        binding.bottom.setMode(BottomNavigationBar.MODE_FIXED)
        binding.bottom.setActiveColor(R.color.titleRed).setBarBackgroundColor(R.color.white)
        binding.bottom.addItem(BottomNavigationItem(R.mipmap.home, "首页")).
                addItem(BottomNavigationItem(R.mipmap.search, "搜索")).
                addItem(BottomNavigationItem(R.mipmap.data, "数据")).
                addItem(BottomNavigationItem(R.mipmap.chat, "社交")).
                addItem(BottomNavigationItem(R.mipmap.person, "我的")).initialise()
        binding.bottom.setTabSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment.newInstance("", "")).commit()
    }

    fun addFragment(frame: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(frame, fragment).commit()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        XPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getAppManager().finishActivity(this)
    }

    @Volatile var backTime = 0
    override fun onBackPressed() {
        if (backTime == 0) {
            val t = Toast.makeText(this, "再按一次退出App", Toast.LENGTH_SHORT)
            t.setGravity(Gravity.CENTER, 0, 0)
            t.show()
            backTime = 1
            thread {
                Thread.sleep(1000)
                backTime = 0
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        AppManager.getAppManager().addActivity(this)
    }
}
