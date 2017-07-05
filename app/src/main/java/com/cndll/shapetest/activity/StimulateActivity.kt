package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityStimulateBinding

class StimulateActivity : BaseActivity<ActivityStimulateBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="我的激励"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_stimulate)
    }
}
