package com.cndll.shapetest.activity

import android.content.Context
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
import com.cndll.shapetest.databinding.ActivitySetPhoneBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.SharedPreferenceUtil
import retrofit2.http.HTTP
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 绑定手机号
 * */
class SetPhoneActivity : BaseActivity<ActivitySetPhoneBinding>() {
    lateinit var context: Context
    lateinit private var cT: MyCountTime
    var type = ""
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_set_phone)
        context = this
        initView()
    }

    private fun initView() {
        cT = MyCountTime(60000, 1000)
        binding.phoneSedCode.setOnClickListener { cT.start() }
        var bundle = this.intent.extras
        type = bundle.getString("type")
        if (type.equals("bPhone")) {
            binding.titlebar.title.text = "绑定手机号"
            binding.oldPhone.visibility = View.GONE
            binding.newPwd.visibility = View.GONE
            binding.phoneSedCode.setOnClickListener {
                if (!Constants.validMobile(binding.phoneNum.text.toString().trim())) {
                    Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                sendCode(binding.phoneNum.text.toString().trim())
            }
        } else if (type.equals("payPwd")) {
            binding.titlebar.title.text = "修改支付密码"
            binding.bindPhone.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
            binding.bindPhone.text = "修改密码"
            binding.newPhone.visibility = View.GONE
            binding.oldPhone.text = "请获取" + SharedPreferenceUtil.read("userPhone", "") + "手机号验证码"
            binding.phoneSedCode.setOnClickListener {
                sendCode(SharedPreferenceUtil.read("userPhone", ""))
            }
        } else if (type.equals("sendPwd")) {
            binding.titlebar.title.text = "设置支付密码"
            binding.bindPhone.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
            binding.bindPhone.text = "设置密码"
            binding.newPhone.visibility = View.GONE
            binding.oldPhone.text = "请获取" + SharedPreferenceUtil.read("userPhone", "") + "手机号验证码"
            binding.phoneSedCode.setOnClickListener {
                sendCode(SharedPreferenceUtil.read("userPhone", ""))
            }
        }

        binding.bindPhone.setOnClickListener { isNull() }

    }

    private fun isNull() {
        var isNull = true
        var msg = ""
        if (binding.phoneCode.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入验证码"
        }
        //绑定手机号
        if (type.equals("bPhone")) {
            if (Constants.validMobile(binding.phoneNum.text.trim().toString())) {
                isNull = false
                msg = "请输入正确手机号"
            }
        } else if (type.equals("payPwd")) {
            if (binding.userNewPwd.text.toString().trim().equals("")) {
                isNull = false
                msg = "请输入新密码"
            }
        }

        if (isNull) {
            if (type.equals("payPwd")) {
                updatePayPhone()
            } else if (type.equals("bPhone")) {
                //////////////////////////////////////////////////////////////////////////////////////////
            } else if (type.equals("sendPwd")) {
                updatePayPhone()
            }
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }


    /** 定时任务 */
    inner class MyCountTime(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {
            binding.phoneSedCode.text = "重新获取"
            binding.phoneSedCode.isClickable = true
        }

        override fun onTick(millisUntilFinished: Long) {
            binding.phoneSedCode.text = ("  " + millisUntilFinished / 1000 + "s后重新获取")
            binding.phoneSedCode.isClickable = false
        }
    }

    /** 修改支付密码*/
    private fun updatePayPhone() {
        AppRequest.getAPI().newLoginPwd("login", "forgotpassword", SharedPreferenceUtil.read("userPhone", ""), binding.userNewPwd.text.toString().trim(), binding.phoneCode.text.toString().trim(), "pay_password").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "设置新密码成功", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(context, t.error_message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    /**发送验证码*/
    private fun sendCode(phone: String) {
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

}
