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
        var bundles = this.intent.extras
        var type: String = bundles.getString("type")
        println("type:" + type)

        if (type.equals("pwd")) {
            binding.titlebar.title.text = "修改密码"
            binding.pwdPayment.visibility = View.VISIBLE
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
        } else if (type.equals("remain")) {
            binding.pwdSig.visibility = View.GONE
            binding.pwdPay.visibility = View.GONE
            binding.pwdCopy.visibility = View.GONE
            binding.pwdDeal.visibility = View.GONE
            binding.pwdHelp.visibility = View.GONE
            binding.linEnterprise.visibility = View.GONE
            binding.linUser.visibility = View.GONE
            binding.linManagEnterprise.visibility = View.GONE
            binding.linManagUser.visibility = View.GONE
            binding.donRedVouchers.visibility = View.VISIBLE
            binding.donCodeAccount.visibility = View.VISIBLE
        }
        //修改登录密码
        binding.pwdSig.setOnClickListener {
            context.startActivity(Intent(context, UpdatePwdActivity::class.java))
        }
        //设置支付密码
        binding.pwdPayment.setOnClickListener {
            bundle.putString("type", "sendPwd")
            context.startActivity(Intent(context, SetPhoneActivity::class.java).putExtras(bundle))
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
            if (bundles.getString("certificate_type").equals("company")) {
                bundle.putString("ID", bundles.getString("ID"))
                context.startActivity(Intent(context, AuthenticationActivity::class.java).putExtras(bundle))
                finish()
            } else {
                context.startActivity(Intent(context, AuthenticationActivity::class.java))
                finish()
            }
        }
        // 个人认证
        binding.linUser.setOnClickListener {
            if (bundles.getString("certificate_type").equals("personal")) {
                bundle.putString("ID", bundles.getString("ID"))
                context.startActivity(Intent(context, PersonalCertificateActivity::class.java).putExtras(bundle))
                finish()
            } else {
                context.startActivity(Intent(context, PersonalCertificateActivity::class.java))
                finish()
            }

        }
        //选择红包
        binding.donRedVouchers.setOnClickListener {
            setResult(101, Intent().putExtra("chers", "voucher"))
            finish()
        }
        //选择抵用卷
        binding.donCodeAccount.setOnClickListener {
            setResult(101, Intent().putExtra("chers", "score"))
            finish()
        }
    }
}
