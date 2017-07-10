package com.cndll.shapetest.activity

import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityApplyBuyBackBinding

/**
 * 激励-申请回购
 * */
class ApplyBuyBackActivity : BaseActivity<ActivityApplyBuyBackBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="申请回购"
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.titleRight.visibility= View.VISIBLE
        binding.titlebar.titleRight.text="记录"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_apply_buy_back)
    }
}
