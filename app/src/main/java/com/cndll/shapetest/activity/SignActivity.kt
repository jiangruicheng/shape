package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.api.bean.response.RegisterResponse
import com.cndll.shapetest.databinding.ActivitySignBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.SharedPreferenceUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SignActivity : BaseActivity<ActivitySignBinding>() {
    /** 输入的手机号 */
    lateinit private var phone: String
    /** 输入的密码 */
    lateinit private var pwd: String
    /** 输入的验证码 */
    lateinit private var code: String
    /** 推荐码 */
    var vouCode = ""


    lateinit private var cT: MyCountTime
    lateinit var type: String

    lateinit var context: Context
    override fun initBindingVar() {

    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener(Clicks())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_sign)
        context = this
        initData()
    }

    /** 点击事件*/
    private fun initData() {
        val bundle = this.intent.extras
        type = bundle.getString("type")
        if (type.equals("pwd")) {
            binding.titlebar.title.text = "忘记密码"
            binding.signRegisterPwd.text = "修改密码"
            binding.signAgreement.visibility = View.GONE
            binding.viewCode.visibility = View.GONE
            binding.linCode.visibility = View.GONE
        } else {
            binding.titlebar.title.text = "账号注册"
            binding.signRegisterPwd.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
        }

        cT = MyCountTime(60000, 1000)
        binding.signRegisterPwd.setOnClickListener(Clicks())
        binding.signSendCode.setOnClickListener(Clicks())
        /**
         * 查看用户协议
         * */
        binding.signAgreement.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type", "sign")
            context.startActivity(Intent(context, UserMessageActivity::class.java).putExtras(bundle))
        }
    }

    /** 获取值 */
    private fun print() {
        phone = binding.signPhone.text.toString().trim()
        pwd = binding.signPwd.text.toString().trim()
        code = binding.signCode.text.toString().trim()
        vouCode = binding.signCodeNum.text.toString().trim()
        var isNull = true
        var msg = ""
        if (!Constants.validMobile(phone)) {
            isNull = false
            msg = "请输入正确的手机号"
        }
        if (code.equals("")) {
            isNull = false
            msg = "请输入验证码"
        }
        if (pwd.equals("")) {
            isNull = false
            msg = "请输入密码"
        }
        if (pwd.length < 6 || pwd.length > 20) {
            isNull = false
            msg = "请输入6至20位密码"
        }
        if (isNull) {
            binding.signRegisterPwd.isClickable = false
            if (type.equals("pwd")) { //忘记密码
                httpNewPwd()
            } else { //注册
                httpSign()
            }
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }


    /** 点击事件 */
    private inner class Clicks : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v!!.id) {
                R.id.sign_register_pwd -> print() //注册
                R.id.back -> finish()             //返回
                R.id.sign_send_code -> sendCode() //发送验证码
            }
        }
    }

    /**发送验证码*/
    private fun sendCode() {
        phone = binding.signPhone.text.toString().trim()
        if (!Constants.validMobile(phone)) {
            Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_LONG).show()
            return
        }
        AppRequest.getAPI().sendCode("login", "sendCode", phone).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "发送成功请注意查收", Toast.LENGTH_LONG).show()
                    cT.start()
                } else {
                    Toast.makeText(context, t.error_message, Toast.LENGTH_LONG).show()
                    return
                }
            }
        })
    }

    /** 定时任务 */
    inner class MyCountTime(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {
            binding.signSendCode.text = "重新获取"
            binding.signSendCode.isClickable = true
        }

        override fun onTick(millisUntilFinished: Long) {
            binding.signSendCode.text = ("  " + millisUntilFinished / 1000 + "s后重新获取")
            binding.signSendCode.isClickable = false
        }
    }

    /**
     * 注册
     * */
    private fun httpSign() {
        AppRequest.getAPI().register("login", "register", phone, pwd, pwd, code, "android", vouCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                binding.signRegisterPwd.isClickable = true
                t as RegisterResponse
                if (t.code == 200) {
                    Toast.makeText(context, "注册成功", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(context, t.error_message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onCompleted() {
                super.onCompleted()
            }
        })
    }

    /**
     * 找回密码
     * */
    private fun httpNewPwd() {
        AppRequest.getAPI().newLoginPwd("login", "forgotpassword", phone, pwd, code, "password").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                binding.signRegisterPwd.isClickable = true
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "找回密码成功", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(context, t.error_message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }

}
