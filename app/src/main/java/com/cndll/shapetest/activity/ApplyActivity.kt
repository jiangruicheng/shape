package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityApplyBinding

/**
 * 申请
 * */
class ApplyActivity : BaseActivity<ActivityApplyBinding>() {

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener{
            finish()
        }
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_apply)
        initView()
    }

    private fun initView(){
        var bundle=this.intent.extras
        var type:String=bundle.getString("type")
        println("type:"+type)
        if(type.equals("sales")){
            binding.titlebar.title.text = "申请成为业务员"
        }else if (type.equals("par")){
            binding.titlebar.title.text = "申请服务合伙人"
        }else if(type.equals("manag")){
            binding.titlebar.title.text = "申请管理合伙人"
            binding.applyDeal.text="管理合伙人协议"
        }else if(type.equals("apply")){
            binding.titlebar.title.text = "申请商家"
        }

    }


}
