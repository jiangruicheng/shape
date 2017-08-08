package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.BankCardAdapter
import com.cndll.shapetest.databinding.ActivityBankCardBinding

/**
 * 银行卡
 * */
class BankCardActivity : BaseActivity<ActivityBankCardBinding>() {
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
            adapter = BankCardAdapter(moreList, context)
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

}
