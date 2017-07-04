package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivitySettingBinding


/**
 * 设置-主界面
 * */
class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    lateinit var context:Context

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
        initBinding(R.layout.activity_setting)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        /** 地址管理 */
        binding.setAddress.setOnClickListener{
            context.startActivity(Intent(context,AddressActivity::class.java))
        }
        // 绑定手机号
        binding.setPhone.setOnClickListener{
            context.startActivity(Intent(context,SettingActivity::class.java))
        }
        //修改密码
        binding.setUpdatePwd.setOnClickListener{
            context.startActivity(Intent(context,SetPwdActivity::class.java))
        }
        //推送消息
        binding.mTogBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                println("选中")
            }else{
                println("未选中")
            }
        }
        //关于我们
        binding.setUsers.setOnClickListener {
            context.startActivity(Intent(context,SetPwdActivity::class.java))
        }
        //退出登录
        binding.outLogin.setOnClickListener {  }


    }

}
