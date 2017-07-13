package com.cndll.shapetest.activity

import android.content.Context
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityUserInfoBinding

/**
 * 个人信息
 * */
class UserInfoActivity : BaseActivity<ActivityUserInfoBinding>() {
    lateinit var context:Context
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text="会员信息"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_user_info)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){



    }

}
