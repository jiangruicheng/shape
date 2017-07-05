package com.cndll.shapetest.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivitySignBinding

class SignActivity : BaseActivity<ActivitySignBinding>() {
    /** 输入的手机号 */
    lateinit private var phone:String
    /** 输入的密码 */
    lateinit private var pwd:String
    /** 输入的验证码 */
    lateinit private var code:String

    lateinit private var cT:MyCountTime

    override fun initBindingVar() {

    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener(Clicks())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_sign)
        initData()
    }

    /** 点击事件*/
    private fun initData(){
        val bundle = this.intent.extras
        val type:String=bundle.getString("type")
        if (type.equals("pwd")){
            binding.signRegisterPwd.text="修改密码"
            binding.signRegisterPwd.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
            binding.signAgreement.visibility=View.GONE
        }else{
            binding.titlebar.title.text = "账号注册"
        }

        cT=MyCountTime(60000,1000)
        binding.signRegisterPwd.setOnClickListener(Clicks())
        binding.signSendCode.setOnClickListener(Clicks())
    }

    /** 获取值 */
    private fun print(){
        phone=binding.signPhone.text.toString().trim()
        pwd=binding.signPwd.text.toString().trim()
        code=binding.signCode.text.toString().trim()
        println("phone:"+phone)
        println("pwd:"+pwd)
        println("code:"+code)
    }



    /** 点击事件 */
    private inner class Clicks:View.OnClickListener{
        override fun onClick(v: View?) {
            when (v!!.id){
                R.id.sign_register_pwd -> print() //注册
                R.id.back -> finish()             //返回
                R.id.sign_send_code -> cT.start() //发送验证码
            }
        }
    }


    /** 定时任务 */
    inner class MyCountTime(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {
            binding.signSendCode.text="重新获取"
            binding.signSendCode.isClickable=true
        }

        override fun onTick(millisUntilFinished: Long) {
            binding.signSendCode.text=("  " + millisUntilFinished / 1000 + "s后重新获取")
            binding.signSendCode.isClickable=false
        }
    }


}
