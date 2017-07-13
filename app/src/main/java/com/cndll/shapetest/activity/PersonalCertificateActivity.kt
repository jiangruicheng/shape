package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityPersonalCertificateBinding

/**
 * 个人认证
 * */
class PersonalCertificateActivity : BaseActivity<ActivityPersonalCertificateBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="个人认证"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_personal_certificate)
    }
}
