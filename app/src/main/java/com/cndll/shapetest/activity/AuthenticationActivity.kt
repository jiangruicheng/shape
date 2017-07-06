package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityAuthenticationBinding

/***
 * 认证企业
 * */
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_authentication)
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        binding.titlebar.title.text="企业认证"

    }
}
