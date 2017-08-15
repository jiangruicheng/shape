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
import com.cndll.shapetest.api.bean.response.ScoreIndexResponse
import com.cndll.shapetest.databinding.ActivityStimulateBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 我的激励
 * */
class StimulateActivity : BaseActivity<ActivityStimulateBinding>() {
    private lateinit var context: Context
    var scoreBean=ScoreIndexResponse.DatasBean()
    var bundle = Bundle()
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "我的激励"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_stimulate)
        context = this
        httpScore()
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        // 认证身份
        binding.sitLinPhoto.setOnClickListener {
            bundle.putString("type", "ent")
            bundle.putString("ID", scoreBean.certificate_id)
            bundle.putString("certificate_type",scoreBean.certificate_type)
            context.startActivity(Intent(context, SetPwdActivity::class.java).putExtras(bundle))
        }
        //回购
        binding.sitRopeText.setOnClickListener {
            context.startActivity(Intent(context, ApplyBuyBackActivity::class.java))
        }
        //直捐
        binding.sitDonateText.setOnClickListener {
            bundle.putString("type", "Donate")
            context.startActivity(Intent(context, DonateActivity::class.java).putExtras(bundle))
        }
        //激励积分
        binding.incentivePointsLin.setOnClickListener {
            bundle.putString("type", "incentive")
            bundle.putString("score", scoreBean.member_excitation_score)
            context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundle))
        }
        //积分明细
        binding.subsidiaryLin.setOnClickListener {
            bundle.putString("type", "subsidiary")
            bundle.putString("score", (scoreBean.member_excitation_score.toDouble() + scoreBean.shop_score.toDouble()).toString())
            context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundle))
        }
        //基金捐款
        binding.fundLin.setOnClickListener {
            bundle.putString("type", "fund")
            context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundle))
        }
        //常用银行卡
        binding.commonBankCardLin.setOnClickListener {
            context.startActivity(Intent(context, BankCardActivity::class.java))
        }
        //消费积分
        binding.scoreLin.setOnClickListener {
            bundle.putString("type", "score")
            bundle.putString("score", scoreBean.shop_score)
            context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundle))
        }
        //我的抵用卷
        binding.offsetVolume.setOnClickListener {
            bundle.putString("type", "voucher")
            context.startActivity(Intent(context, VouchersActivity::class.java).putExtras(bundle))
        }
        //积分转增
        binding.integralRemainderLin.setOnClickListener {
            bundle.putString("type", "Remain")
            context.startActivity(Intent(context, DonateActivity::class.java).putExtras(bundle))
        }
        //回购记录
        binding.buybackRecordLin.setOnClickListener {
            bundle.putString("type", "buyBack")
            context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundle))
        }

    }

    override fun onResume() {
        super.onResume()
        httpScore()
    }

    /**
     * 首页数据
     * */
    private fun httpScore() {
        AppRequest.getAPI().soreIndex("score", "index", SharedPreferenceUtil.read("key", "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as ScoreIndexResponse
                if (t.code == 200) {
                    scoreBean=t.datas
                    if (t.datas.state == 0) {
                        binding.stimulateType.text = "身份认证中，请等待认证"
                        binding.stimulateMoneySafety.text = "为资金安全，请认证"
                        binding.sitLinPhoto.isClickable = false
                    } else if (t.datas.state == 1) {
                        binding.stimulateType.text = "身份认证成功"
                        binding.stimulateMoneySafety.text = "为资金安全，请认证"
                        binding.sitLinPhoto.isClickable = false
//                        binding.sitLinPhoto.visibility=View.GONE
                    } else if (t.datas.state == 2) {
                        binding.stimulateType.text = "身份认证失败，请重新认证"
                        binding.stimulateMoneySafety.text = "为资金安全，请认证"
                    } else if (t.datas.state == 9) {
                        binding.stimulateType.text = "身份认证需认证，请认证身份"
                        binding.stimulateMoneySafety.text = "为资金安全，请认证"
                    }
//                    t.datas.certificate_id   认证的ID
//                    t.datas.certificate_type  ’personal‘个人认证’company‘企业认证

                    binding.stimulateCode.text = t.datas.member_excitation_score //总积分
                    binding.stimulatePurstCode.text = t.datas.member_excitation_score + "积分"//激励积分
                    binding.stimulateIntegCode.text = (t.datas.member_excitation_score.toDouble() + t.datas.shop_score.toDouble()).toString() + "积分"//积分明细
                    binding.stimulateDonateCode.text = t.datas.fund.toString() + "积分"//基金捐款
                    binding.stimulateBankCode.text = t.datas.bank_card_num + "张"//常用银行卡
                    binding.stimulateBanlanceCode.text = t.datas.shop_score + "积分"//消费积分
                    binding.stimulateRollCode.text = t.datas.voucher_num.toString() + "张"//我的抵用卷

                } else {
                    Toast.makeText(context, t.error_message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
