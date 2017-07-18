package com.cndll.shapetest.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityUpdatePwdBinding
/**
 * 修改密码
 * */
class UpdatePwdActivity : BaseActivity<ActivityUpdatePwdBinding>() {
    private lateinit var context:Context
    override fun initBindingVar() {

    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_update_pwd)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        binding.titlebar.title.text="修改登录密码"
        binding.updatePwd.setOnClickListener {
            isNull()
        }
    }

    private fun isNull(){
        var isNull=true
        var msg=""
        if(binding.pwdUpdate.text.toString().trim().equals("")){
            isNull=false
            msg="请输入原密码"
        }
        if(binding.newPwdUpdate.text.toString().trim().equals("")){
            isNull=false
            msg="请输入新密码"
        }
        if (6>binding.newPwdUpdate.text.toString().trim().length ||binding.newPwdUpdate.text.toString().trim().length>20){
            isNull=false
            msg="请输入6至20位密码"
        }
        if(isNull){


        }else{
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
            return
        }

    }




}
