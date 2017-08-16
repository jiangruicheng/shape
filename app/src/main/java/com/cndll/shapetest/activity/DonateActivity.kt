package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityDonateBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * 直捐
 * */
class DonateActivity : BaseActivity<ActivityDonateBinding>() {
    lateinit var context: Context
    var bundles = Bundle()
    var scoreType: String = "score"

    override fun initBindingVar() {
    }

    override fun initTitle() {

        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.titleRight.visibility = View.VISIBLE
        binding.titlebar.titleRight.text = "记录"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_donate)
        context = this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {

        var bundle = this.intent.extras
        var type = bundle.getString("type")
        if (type.equals("Donate")) {
            binding.titlebar.title.text = "直捐"
            binding.idUserNum.visibility = View.GONE
            binding.linDonateType.visibility = View.GONE
            binding.titlebar.titleRight.setOnClickListener {
                bundles.putString("type", "donate")
                context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundles))
            }
        } else if (type.equals("Remain")) {
            binding.titlebar.title.text = "积分转增"
            bundles.putString("type", "remain")
            binding.titlebar.titleRight.setOnClickListener {
                //查看记录
                context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundles))
            }
            binding.donateChoseText.setOnClickListener {
                //选择选中类型
                startActivityForResult(Intent(context, SetPwdActivity::class.java).putExtras(bundles), 101)
            }
            binding.donateSubmit.setOnClickListener {
                isNull()
            }
        }


    }

    /**
     * 非空判断
     * */
    private fun isNull() {
        var msg = ""
        var isNull = true
        if (binding.donateID.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入转增人ID"
        }
        if (binding.donateAccount.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入直捐的积分额度"
        }
        if (binding.donatePayPwd.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入支付密码"
        }

        if (isNull) {
            httpScore()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            return
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            if (resultCode == 101) {
                scoreType = data!!.extras.getString("chers")
                if (scoreType.equals("score")) {
                    binding.donateChoseText.text = "激励积分"
                } else if (scoreType.equals("voucher")) {
                    binding.donateChoseText.text = "通用抵用卷"
                }

            }

        }
    }


    /***
     * 积分转增
     * */
    private fun httpScore() {
        AppRequest.getAPI().scoreOperation("score", "donation_operation", SharedPreferenceUtil.read("key", ""), binding.donateAccount.text.toString().trim(), binding.donatePayPwd.text.toString().trim(), binding.donateID.text.toString().trim(), scoreType).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "转增成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(context, t.error_message, Toast.LENGTH_SHORT).show()
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
