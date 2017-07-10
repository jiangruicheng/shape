package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityStimulateBinding

/**
 * 我的激励
 * */
class StimulateActivity : BaseActivity<ActivityStimulateBinding>() {
    private lateinit var context:Context
    var bundle=Bundle()
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="我的激励"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_stimulate)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        // 认证身份
        binding.sitLinPhoto.setOnClickListener {
            bundle.putString("type","ent")
            context.startActivity(Intent(context,SetPwdActivity::class.java).putExtras(bundle))
        }
        //回购
        binding.sitRopeText.setOnClickListener {
            context.startActivity(Intent(context,ApplyBuyBackActivity::class.java))
        }
        //直捐
        binding.sitDonateText.setOnClickListener {
            bundle.putString("type","Donate")
            context.startActivity(Intent(context,DonateActivity::class.java).putExtras(bundle))
        }
        //激励积分
        binding.incentivePointsLin.setOnClickListener {
            context.startActivity(Intent(context,IntegralActivity::class.java))
        }
        //积分明细
        binding.subsidiaryLin.setOnClickListener {
            context.startActivity(Intent(context,IntegralActivity::class.java))
        }
        //基金捐款
        binding.fundLin.setOnClickListener {
            context.startActivity(Intent(context,IntegralActivity::class.java))
        }
        //常用银行卡
        binding.commonBankCardLin.setOnClickListener {
            context.startActivity(Intent(context,BankCardActivity::class.java))
        }
        //消费积分
        binding.scoreLin.setOnClickListener {
            context.startActivity(Intent(context,IntegralActivity::class.java))
        }
        //我的抵用卷
        binding.offsetVolume.setOnClickListener {
            context.startActivity(Intent(context,VouchersActivity::class.java))
        }
        //积分转增
        binding.integralRemainderLin.setOnClickListener {
            bundle.putString("type","Remain")
            context.startActivity(Intent(context,DonateActivity::class.java).putExtras(bundle))
        }
        //回购记录
        binding.buybackRecordLin.setOnClickListener {
            context.startActivity(Intent(context,IntegralActivity::class.java))
        }


    }
}
