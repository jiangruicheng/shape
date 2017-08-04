package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.databinding.ActivitySetPwdBinding

class SetPwdActivity : BaseActivity<ActivitySetPwdBinding>() {
    private lateinit var context: Context
    var bundle = Bundle()
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_set_pwd)
        context = this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        var bundle = this.intent.extras
        var type: String = bundle.getString("type")
        println("type:" + type)

        if (type.equals("pwd")) {
            binding.titlebar.title.text = "修改密码"
            binding.pwdCopy.visibility = View.GONE
            binding.pwdDeal.visibility = View.GONE
            binding.pwdHelp.visibility = View.GONE
            binding.linEnterprise.visibility = View.GONE
            binding.linUser.visibility = View.GONE
            binding.authType.visibility = View.GONE
            binding.linManagEnterprise.visibility = View.GONE
            binding.linManagUser.visibility = View.GONE
        } else if (type.equals("users")) {
            binding.titlebar.title.text = "关于我们"
            binding.pwdSig.visibility = View.GONE
            binding.pwdPay.visibility = View.GONE
            binding.linEnterprise.visibility = View.GONE
            binding.linUser.visibility = View.GONE
            binding.authType.visibility = View.GONE
            binding.linManagEnterprise.visibility = View.GONE
            binding.linManagUser.visibility = View.GONE
        } else if (type.equals("ent")) {
            binding.titlebar.title.text = "身份认证"
            binding.pwdSig.visibility = View.GONE
            binding.pwdPay.visibility = View.GONE
            binding.pwdCopy.visibility = View.GONE
            binding.pwdDeal.visibility = View.GONE
            binding.pwdHelp.visibility = View.GONE
            binding.linManagEnterprise.visibility = View.GONE
            binding.linManagUser.visibility = View.GONE
        } else if (type.equals("manag")) {
            binding.titlebar.title.text = "请选择身份认证"
            binding.pwdSig.visibility = View.GONE
            binding.pwdPay.visibility = View.GONE
            binding.pwdCopy.visibility = View.GONE
            binding.pwdDeal.visibility = View.GONE
            binding.pwdHelp.visibility = View.GONE
            binding.linEnterprise.visibility = View.GONE
            binding.linUser.visibility = View.GONE
        }

        //申请企业合伙人
        binding.linManagEnterprise.setOnClickListener {
            context.startActivity(Intent(context, PersonalCertificateManagActivity::class.java))
        }
        //申请个人合伙人
        binding.linManagUser.setOnClickListener {
            context.startActivity(Intent(context, AuthenticationManagActivity::class.java))
        }

        //修改登录密码
        binding.pwdSig.setOnClickListener {
            context.startActivity(Intent(context, UpdatePwdActivity::class.java))
        }
        //修改支付密码
        binding.pwdPay.setOnClickListener {
            bundle.putString("type", "payPwd")
            context.startActivity(Intent(context, SetPhoneActivity::class.java).putExtras(bundle))
        }
        //版权信息
        binding.pwdCopy.setOnClickListener {
            bundle.putString("type", "copy")
            context.startActivity(Intent(context, UserMessageActivity::class.java).putExtras(bundle))

        }
        //使用协议
        binding.pwdDeal.setOnClickListener {
            bundle.putString("type", "deal")
            context.startActivity(Intent(context, UserMessageActivity::class.java).putExtras(bundle))

        }
        //帮助中心
        binding.pwdHelp.setOnClickListener {
            bundle.putString("type", "help")
            context.startActivity(Intent(context, UserMessageActivity::class.java).putExtras(bundle))
        }
        //企业认证
        binding.linEnterprise.setOnClickListener {
            context.startActivity(Intent(context, AuthenticationActivity::class.java))
        }
        // 个人认证
        binding.linUser.setOnClickListener {
            context.startActivity(Intent(context, PersonalCertificateActivity::class.java))
        }
    }
}
