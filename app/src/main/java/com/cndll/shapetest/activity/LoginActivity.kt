package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.RegisterResponse
import com.cndll.shapetest.bean.Login
import com.cndll.shapetest.config.AppContext
import com.cndll.shapetest.databinding.ActivityLoginBinding
import com.cndll.shapetest.event.HandlerClick
import com.cndll.shapetest.tools.*
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

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
           httpLogin()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }

    /**
     * 登录
     * */
    private fun httpLogin(){
        AppRequest.getAPI().login("login","index",binding.username.text.toString().trim(),binding.password.text.toString().trim(),"android").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object  : BaseObservable(){
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as RegisterResponse
                if(t.code==200){
                    Toast.makeText(context,"登录成功",Toast.LENGTH_LONG).show()
                    SharedPreferenceUtil.insert("userPhone",t.datas.username)
                    SharedPreferenceUtil.insert("key",t.datas.key)
                    binding.handler.login(binding.loginbtn)
                    finish()
                }else{
                    Toast.makeText(context,"登录失败,请检查密码和手机号",Toast.LENGTH_LONG).show()
                    return
                }
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }
        })
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
