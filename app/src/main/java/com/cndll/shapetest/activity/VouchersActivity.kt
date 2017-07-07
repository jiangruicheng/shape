package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityVouchersBinding

/**
 * 我的抵用卷
 * */
class VouchersActivity : BaseActivity<ActivityVouchersBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text="我的抵用卷"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_vouchers)
    }
}
