package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityAddBankBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.cndll.shapetest.wxapi.AppRegister
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 添加银行卡
 * */
class AddBankActivity : BaseActivity<ActivityAddBankBinding>() {
    lateinit var context: Context
    lateinit var cT: MyCountTime
    var bankNum: String = ""
    var bundle = Bundle()
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "添加银行卡"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_add_bank)
        var bundles = this.intent.extras
        bankNum = bundles.getString("cardNum")
        context = this
        cT = MyCountTime(60000, 1000)
        /**
         * 成功，失败跳转
         * */
        binding.bankNext.setOnClickListener {
            isNull()
        }

        //查看协议
        binding.applyDeal.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type", "bank")
            context.startActivity(Intent(context, UserMessageActivity::class.java).putExtras(bundle))
        }

        binding.bankChose.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.bankNext.isClickable = true
                binding.bankNext.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
            } else {
                binding.bankNext.isClickable = false
                binding.bankNext.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_gray))
            }
        }
        //发送验证码
        binding.bankSendCode.setOnClickListener {
            if (!Constants.validMobile(binding.bankPhone.text.toString().trim())) {
                Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            AppRequest.getAPI().sendCode("login", "sendCode", binding.bankPhone.text.toString().trim()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
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

                override fun onCompleted() {
                    super.onCompleted()
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    e!!.printStackTrace()
                }
            })


        }
    }

    /**
     * 验证码-定时任务
     * */
    inner class MyCountTime(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {
            binding.bankSendCode.text = "重新获取"
            binding.bankSendCode.isClickable = true
        }

        override fun onTick(millisUntilFinished: Long) {
            binding.bankSendCode.text = ("" + millisUntilFinished / 1000 + "s后重新获取")
            binding.bankSendCode.isClickable = false
        }
    }

    /**
     * 非空验证
     * */
    private fun isNull() {
        var msg = ""
        var isNull = true
        if (binding.bankCardName.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入持卡人"
        }
        if (binding.bankCardNum.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入银行卡号"
        }
        if (!Constants.validMobile(binding.bankPhone.text.toString().trim())) {
            isNull = false
            msg = "请输入正确的手机号"
        }
        if (binding.bankCardCode.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入验证码"
        }
        if (isNull) {
            httpBank()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            return
        }

    }

    /**
     * 提交添加银行卡
     * */
    private fun httpBank() {
        AppRequest.getAPI().addBankCard("bank_card", "bankAdd", SharedPreferenceUtil.read("key", ""), binding.bankCardName.text.toString().trim(), bankNum, binding.bankCardCode.text.toString().trim(), binding.bankCardNum.text.toString().trim(), binding.bankPhone.text.toString().trim()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
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
                    bundle.putString("is", "ok")
                    context.startActivity(Intent(context, AddCarStareActivity::class.java).putExtras(bundle))
                    finish()
                } else {
                    bundle.putString("is", "no")
                    context.startActivity(Intent(context, AddCarStareActivity::class.java).putExtras(bundle))
                    Toast.makeText(context,t.error_message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
