package com.cndll.shapetest.activity

import android.content.Context
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivitySendNewBinding

class SendNewActivity : BaseActivity<ActivitySendNewBinding>() {
    lateinit var context: Context

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text = "消息"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_send_new)
        context = this
    }
}
