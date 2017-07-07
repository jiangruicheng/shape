package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityIntegralBinding

/**
 * 积分数据列表
 * */
class IntegralActivity : BaseActivity<ActivityIntegralBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="激励积分"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_integral)
    }
}
