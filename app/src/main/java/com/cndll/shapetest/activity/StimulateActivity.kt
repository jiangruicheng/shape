package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityStimulateBinding

/**
 * 我的激励
 * */
class StimulateActivity : BaseActivity<ActivityStimulateBinding>() {
    private lateinit var context:Context
    var bundle=Bundle()
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="我的激励"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_stimulate)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        // 认证身份
        binding.sitLinPhoto.setOnClickListener {
            bundle.putString("type","ent")
            context.startActivity(Intent(context,SetPwdActivity::class.java).putExtras(bundle))
        }
    }
}
