package com.cndll.shapetest.activity

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.BankCardAdapter
import com.cndll.shapetest.databinding.ActivityBankCardBinding

/**
 * 银行卡
 * */
class BankCardActivity : BaseActivity<ActivityBankCardBinding>(), BankCardAdapter.setOnClick {
    override fun bankDelete(position: Int) {
        dealView()
    }

    lateinit var context: Context
    var adapter: BankCardAdapter? = null
    var moreList = ArrayList<ContentValues>()

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "常用银行卡"
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.menuDate.visibility = View.VISIBLE
        binding.titlebar.menuDate.setImageResource(R.mipmap.add_card)
        binding.titlebar.menuDate.setOnClickListener {
            context.startActivity(Intent(context, AddBankActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_bank_card)
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

        var cv = ContentValues()
        cv.put("nun", "**** **** **** 1123")
        var cv1 = ContentValues()
        cv1.put("nun", "**** **** **** 1123")
        moreList.add(cv)
        moreList.add(cv1)
        adapter!!.notifyDataSetChanged()
    }

    /**
     * 刷新数据
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onResume() {
        super.onResume()

    }

    /**
     * 对话框
     * */
    private fun dealView() {
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
        title.text = "是否删除尾号为1234的银行卡"
        chose.setOnClickListener {
            dialog.dismiss()
        }
        ok.setOnClickListener {
            dialog.dismiss()
        }
    }
}
