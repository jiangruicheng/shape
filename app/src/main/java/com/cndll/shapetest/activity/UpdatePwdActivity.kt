package com.cndll.shapetest.activity

import android.content.Context
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityUpdatePwdBinding
/**
 * 修改密码
 * */
class UpdatePwdActivity : BaseActivity<ActivityUpdatePwdBinding>() {
    private lateinit var context:Context
    override fun initBindingVar() {

    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_update_pwd)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        binding.titlebar.title.text="修改登录密码"
    }


}
