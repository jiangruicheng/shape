package com.cndll.shapetest.activity

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.BankCardAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.BankCardResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityBankCardBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 银行卡
 * */
class BankCardActivity : BaseActivity<ActivityBankCardBinding>(), BankCardAdapter.setOnClick {
    override fun bankDelete(position: Int) {
        dealView(moreList[position].id, moreList[position].card_num, moreList[position].bank_name)
    }

    lateinit var context: Context
    var adapter: BankCardAdapter? = null
    var moreList = ArrayList<BankCardResponse.DatasBean.BankInfoBean>()
    var bankNum: String = ""

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "常用银行卡"
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.menuDate.visibility = View.VISIBLE
        binding.titlebar.menuDate.setImageResource(R.mipmap.add_card)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_bank_card)
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this@BankCardActivity))
        context = this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        if (adapter == null) {
            adapter = BankCardAdapter(moreList, context, this)
            binding.bankCardList.adapter = adapter
        }
        binding.titlebar.menuDate.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("cardNum", bankNum)
            context.startActivity(Intent(context, AddBankActivity::class.java).putExtras(bundle))
        }
    }

    override fun onResume() {
        super.onResume()
        httpBankList()
    }

    /**
     * 对话框
     * */
    private fun dealView(id: String, num: String, cardType: String) {
        val dm = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        val dialog = Dialog(context, R.style.AlertDialog)
        dialog!!.setContentView(R.layout.deal_content)
        dialog!!.setCanceledOnTouchOutside(true)
        dialog!!.show()
        // 设置对话框大小
        val layoutParams = dialog!!.window.attributes
        layoutParams.width = (dm.widthPixels * 0.8).toInt()
        layoutParams.height = (dm.heightPixels * 0.9).toInt()
        dialog!!.window.attributes = layoutParams

        val title = dialog!!.findViewById(R.id.deal_title) as TextView
        val ok = dialog!!.findViewById(R.id.deal_ok) as TextView
        val chose = dialog!!.findViewById(R.id.deal_close) as TextView
        title.text = "是否删除" + cardType + "（尾号为" + num.substring(num.length - 4, num.length) + "）的银行卡"
        ok.text = "取消"
        ok.setTextColor(resources.getColor(R.color.contents_text))
        chose.text = "删除"
        chose.setTextColor(resources.getColor(R.color.contents_text))
        chose.setOnClickListener {
            dialog.dismiss()
            deleteBank(id)
        }
        ok.setOnClickListener {
            dialog.dismiss()
        }
    }

    /**
     * 银行卡列表
     * */
    private fun httpBankList() {
        if (moreList != null && moreList.size > 0) {
            moreList.clear()
        }
        AppRequest.getAPI().bankCardList("bank_card", "index", SharedPreferenceUtil.read("key", "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as BankCardResponse
                if (t.code == 200) {
                    moreList.addAll(t.datas.bank_info)
                    bankNum = t.datas.card_num
                    adapter!!.notifyDataSetChanged()
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

    /**
     * 删除银行卡
     * */
    private fun deleteBank(id: String) {
        AppRequest.getAPI().deleteBankCard("bank_card", "deleteBank", SharedPreferenceUtil.read("key", ""), id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    httpBankList()
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
