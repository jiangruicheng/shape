package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.bean.Login
import com.cndll.shapetest.config.AppContext
import com.cndll.shapetest.databinding.ActivityLoginBinding
import com.cndll.shapetest.event.HandlerClick
import com.cndll.shapetest.tools.AppManager
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.Ini
import com.cndll.shapetest.tools.UtilsUmeng
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    /**
     * 登录之后
     * */
    lateinit private var mHandler: Handler
    lateinit var context: Context

    override fun initTitle() {
        binding.titlebar.title.text = "手机号登录"
        // 退出登录
        binding.titlebar.back.setOnClickListener { AppContext.getInstance().logoutApp() }
    }

    override fun initBindingVar() {
        binding.login = Login()
        binding.handler = HandlerClick()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_login)
        context=this
        initView()
        binding.qq.setOnClickListener {
            UtilsUmeng.Login(this@LoginActivity, applicationContext, SHARE_MEDIA.QQ, mHandler)

        }
        binding.wechat.setOnClickListener {
            UtilsUmeng.Login(this@LoginActivity, applicationContext, SHARE_MEDIA.WEIXIN, mHandler)
        }
        binding.loginbtn.setOnClickListener { isNull() }
    }

    private fun isNull() {
        var isNull = true
        var msg = ""
        if (!Constants.validMobile(binding.username.text.toString().trim())) {
            isNull = false
            msg = "请输入正确的手机号"
        }
        if (binding.password.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入密码"
        }
        if (isNull) {
            binding.handler.login(binding.loginbtn)
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }

    }

    private fun initView() {
        mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    Ini.SDK_PAY_FLAG3 -> {
                        println("拿到第三方登录的信息,请求自己的接口登录！")
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

    override fun onResume() {
        super.onResume()
        AppManager.getAppManager().addActivity(this)
    }

}
