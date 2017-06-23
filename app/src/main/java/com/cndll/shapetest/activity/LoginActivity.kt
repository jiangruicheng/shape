package com.cndll.shapetest.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.ImageView
import com.cndll.shapetest.R
import com.cndll.shapetest.bean.Login
import com.cndll.shapetest.databinding.ActivityLoginBinding
import com.cndll.shapetest.event.HandlerClick
import com.cndll.shapetest.tools.Ini
import com.cndll.shapetest.tools.UtilsUmeng
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    /**
     * 登录之后
     * */
    lateinit var mHandler:Handler

    override fun initTitle() {
        binding.titlebar.title.text = "手机登录"
    }

    override fun initBindingVar() {
        binding.login = Login()
        binding.handler = HandlerClick()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_login)
        initView()
        binding.qq.setOnClickListener {
            UtilsUmeng.Login(this@LoginActivity, applicationContext, SHARE_MEDIA.QQ, mHandler)

        }
        binding.wechat.setOnClickListener {

            UtilsUmeng.Login(this@LoginActivity, applicationContext, SHARE_MEDIA.WEIXIN, mHandler)
        }
    }



   private fun initView(){
       mHandler = object : Handler() {
           override fun handleMessage(msg: Message) {
               when (msg.what) {
                   Ini.SDK_PAY_FLAG3 -> {
                       println("请求自己的接口登录！")
                   }
               }
           }
       }

   }





    /**
     * 第三方登录
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }

}
