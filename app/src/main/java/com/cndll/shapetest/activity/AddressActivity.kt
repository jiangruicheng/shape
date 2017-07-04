package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityAddressBinding

/**
 * 地址列表
 * */
class AddressActivity : BaseActivity<ActivityAddressBinding>() {
    lateinit var content:Context

    override fun initBindingVar() {

    }

    override fun initTitle() {
        binding.titlebar.title.text = "设置"
        binding.titlebar.back.setOnClickListener{
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_address)
        content=this
        initView()
    }

    /** 加载控件 */
    private fun initView(){
        // 跳转地址
        binding.addAddress.setOnClickListener{
            startActivity(Intent(content,AddListAddressActivity::class.java))
        }


    }

}
