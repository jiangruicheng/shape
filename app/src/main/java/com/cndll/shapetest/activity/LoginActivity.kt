package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.bean.Login
import com.cndll.shapetest.databinding.ActivityLoginBinding
import com.cndll.shapetest.event.HandlerClick

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun initTitle() {
        binding.titlebar.title.text = "手机登录"
    }

    override fun initBindingVar() {
        binding.login = Login()
        binding.handler = HandlerClick()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_login)
    }
}
