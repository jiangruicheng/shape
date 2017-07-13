package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityAddBankBinding

class AddBankActivity : BaseActivity<ActivityAddBankBinding>() {
    lateinit var context:Context
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="添加银行卡"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_add_bank)
        context=this
        /**
         * 成功，失败跳转
         * */
        binding.bankNext.setOnClickListener {
            context.startActivity(Intent(context,AddCarStareActivity::class.java))
        }
    }
}
