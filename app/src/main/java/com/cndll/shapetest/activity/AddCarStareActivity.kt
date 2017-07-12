package com.cndll.shapetest.activity

import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityAddCarStareBinding

/**
 * 添加卡之后的状态
 * */
class AddCarStareActivity : BaseActivity<ActivityAddCarStareBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="添加银行卡"
        binding.titlebar.back.visibility=View.INVISIBLE
        binding.titlebar.titleRight.visibility=View.VISIBLE
        binding.titlebar.titleRight.text="完成"
        binding.titlebar.titleRight.setTextColor(resources.getColor(R.color.titleRed))
        binding.titlebar.titleRight.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_add_car_stare)


//        binding.linFinish

//        binding.linJam

    }
}
