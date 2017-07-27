package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityUserMessageBinding

/**
 * 关于我们，版权信息等等
 * */
class UserMessageActivity : BaseActivity<ActivityUserMessageBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_user_message)
        initView()
    }

    /**
     * 绑定信息
     * */
    private fun initView(){
        var bundle=this.intent.extras
        var type=bundle.getString("type")
        if (type.equals("copy")){
            binding.titlebar.title.text="关于我们"
            binding.messageContent.text="关于我们"
        }else if(type.equals("deal")){
            binding.titlebar.title.text="使用协议"
            binding.messageContent.text="使用协议"
        }else if (type.equals("help")){
            binding.titlebar.title.text="帮助中心"
            binding.messageContent.text="帮助中心"
        }else if (type.equals("sign")){
            binding.titlebar.title.text="用户协议"
            binding.messageContent.text="用户协议"
        }else if(type.equals("apply")){
            binding.titlebar.title.text="商家协议"
            binding.messageContent.text="商家协议--管理合伙人协议"
        }else if (type.equals("applyDeal")){
            binding.titlebar.title.text="基金捐款协议"
            binding.messageContent.text="基金捐款协议"
        }else if(type.equals("bank")){
            binding.titlebar.title.text="服务协议"
            binding.messageContent.text="服务协议"
        }


    }

}
