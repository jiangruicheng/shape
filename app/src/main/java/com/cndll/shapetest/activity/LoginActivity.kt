package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.ApiUtill
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
    var userNmae = ""
    var passWord = ""
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
        context = this
        initView()
        binding.qq.setOnClickListener {
            UtilsUmeng.Login(this@LoginActivity, applicationContext, SHARE_MEDIA.QQ, mHandler)
            SharedPreferenceUtil.insert("logType", "qq")
        }
        binding.wechat.setOnClickListener {
            UtilsUmeng.Login(this@LoginActivity, applicationContext, SHARE_MEDIA.WEIXIN, mHandler)
            SharedPreferenceUtil.insert("logType", "wx")
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
            userNmae = binding.username.text.toString().trim()
            passWord = binding.password.text.toString().trim()
            httpLogin(userNmae, passWord)
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }

    /**
     * 登录
     * */
    private fun httpLogin(userName: String, paw: String) {
        AppRequest.getAPI().login("login", "index", userName, paw, "android").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as RegisterResponse
                if (t.code == 200) {
                    Toast.makeText(context, "登录成功", Toast.LENGTH_LONG).show()
                    if (!SharedPreferenceUtil.hasKey("userName")) {
                        SharedPreferenceUtil.insert("userName", binding.username.text.toString().trim())
                        SharedPreferenceUtil.insert("passWord", binding.password.text.toString().trim())
                        SharedPreferenceUtil.insert("userPhone", t.datas.username)
                        SharedPreferenceUtil.insert("key", t.datas.key)
                    }
                    binding.handler.login(binding.loginbtn)
                    finish()
                } else {
                    Toast.makeText(context, t.error_message, Toast.LENGTH_LONG).show()
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

    /**
     * 第三方登录
     * */
    private fun httpLoginQQ() {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().qqLogin("login", "thirdLogin", SharedPreferenceUtil.read("openid", ""), SharedPreferenceUtil.read("logType", ""), "android"), {
            baseResponse ->
            baseResponse as RegisterResponse
            if (baseResponse.code == 200) {
                if (baseResponse.datas.member_name == null) {
                    var bundle = Bundle()
                    bundle.putString("type", "qq")
                    context.startActivity(Intent(context, SignActivity::class.java).putExtras(bundle))
                } else {
                    SharedPreferenceUtil.insert("userPhone", baseResponse.datas.member_name)
                    SharedPreferenceUtil.insert("key", baseResponse.datas.token)
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()
                    binding.handler.login(binding.loginbtn)
                    finish()
                }
            } else if (baseResponse.code == 400) {
                Toast.makeText(context, baseResponse.datas.error, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initView() {
        if (SharedPreferenceUtil.hasKey("userName")) {
            userNmae = SharedPreferenceUtil.read("userName", "")
            passWord = SharedPreferenceUtil.read("passWord", "")
            httpLogin(userNmae, passWord)
        } else if (SharedPreferenceUtil.hasKey("openid")) {
            httpLoginQQ()
        }

        mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    Ini.SDK_PAY_FLAG3 -> {
                        httpLoginQQ()
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
