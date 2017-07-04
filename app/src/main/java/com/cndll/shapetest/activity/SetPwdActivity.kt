package com.cndll.shapetest.activity

import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivitySetPwdBinding

class SetPwdActivity : BaseActivity<ActivitySetPwdBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener{
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_set_pwd)
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        var bundle=this.intent.extras
        var type:String=bundle.getString("type")
        println("type:"+type)

        if (type.equals("pwd")){
            binding.titlebar.title.text="修改密码"
            binding.pwdCopy.visibility= View.GONE
            binding.pwdDeal.visibility= View.GONE
            binding.pwdHelp.visibility= View.GONE
        }else if (type.equals("users")){
            binding.titlebar.title.text="关于我们"
            binding.pwdSig.visibility= View.GONE
            binding.pwdPay.visibility= View.GONE
        }

        //修改登录密码
        binding.pwdSig.setOnClickListener{}
        //修改支付密码
        binding.pwdPay.setOnClickListener{}
        //版权信息
        binding.pwdCopy.setOnClickListener{}
        //使用协议
        binding.pwdDeal.setOnClickListener{}
        //帮助中心
        binding.pwdHelp.setOnClickListener{}

    }
}
