package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityBankCardBinding

/**
 * 银行卡
 * */
class BankCardActivity : BaseActivity<ActivityBankCardBinding>() {
    lateinit var context:Context
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="常用银行卡"
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.menu.visibility= View.VISIBLE

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_bank_card)
        context=this
        binding.titlebar.menu.setOnClickListener {
            context.startActivity(Intent(context,AddBankActivity::class.java))
        }

    }

    /**
     * 刷新数据
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onResume() {
        super.onResume()

    }

}
