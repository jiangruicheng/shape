package com.cndll.shapetest.activity

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivitySetPhoneBinding

/**
 * 绑定手机号
 * */
class SetPhoneActivity : BaseActivity<ActivitySetPhoneBinding>() {
    lateinit var context:Context
    lateinit private var cT:MyCountTime
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="绑定手机号"
        binding.titlebar.back.setOnClickListener{
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_set_phone)
        context=this
        initView()
    }


    private fun initView(){
        cT=MyCountTime(60000,1000)
        binding.phoneSedCode.setOnClickListener { cT.start() }
    }

    /** 定时任务 */
    inner class MyCountTime(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {
            binding.phoneSedCode.text="重新获取"
            binding.phoneSedCode.isClickable=true
        }

        override fun onTick(millisUntilFinished: Long) {
            binding.phoneSedCode.text=("  " + millisUntilFinished / 1000 + "s后重新获取")
            binding.phoneSedCode.isClickable=false
        }
    }

}
