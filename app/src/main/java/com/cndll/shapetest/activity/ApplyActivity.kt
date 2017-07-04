package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityApplyBinding

/**
 * 申请
 * */
class ApplyActivity : BaseActivity<ActivityApplyBinding>() {
    override fun initTitle() {
        binding.titlebar.title.text = "账号注册"
//        binding.titlebar.back.setOnClickListener(Clicks())
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_apply)
    }
}
