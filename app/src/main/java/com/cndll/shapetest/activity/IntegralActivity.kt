package com.cndll.shapetest.activity

import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityIntegralBinding
import java.util.*
import kotlin.collections.ArrayList

/**
 * 积分数据列表
 * */
class IntegralActivity : BaseActivity<ActivityIntegralBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.menu.visibility=View.VISIBLE
        binding.titlebar.menu.setImageResource(R.mipmap.date_sache)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_integral)
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        var bundle=this.intent.extras
        var type=bundle.getString("type")

        if(type.equals("incentive")){
            binding.titlebar.title.text="激励积分"
            binding.manageTx.visibility=View.GONE

        }else if(type.equals("score")){
            binding.titlebar.title.text="消费积分"
            binding.manageTx.visibility=View.GONE
            binding.funTx.visibility=View.GONE
            binding.typeTx.text="消费店铺"

        }else if(type.equals("subsidiary")){
            binding.titlebar.title.text="积分明细"
            binding.manageTx.visibility=View.GONE
            binding.funTx.visibility=View.GONE
            binding.typeTx.text="商品名称"
        }else if (type.equals("fund")){
            binding.titlebar.title.text="基金捐款"
            binding.manageTx.visibility=View.GONE
            binding.typeTx.visibility=View.GONE
        }
        date()
    }
    /**
     * 获取日期
     * */
    private fun date(){
        var listDate=ArrayList<String>()
        var c=Calendar.getInstance()
        var year=c.get(Calendar.YEAR)
        var month=c.get(Calendar.MONTH)+1
        if(month<7){
            listDate.add(""+month)
            for (i in 0..6) {
                if(month==1){
                    month=12
                    year=year - 1
                    listDate.add(""+year+"年"+(month)+"月")
                    continue
                }
                listDate.add(""+year+"年"+(month-1)+"月")
                month--
            }
        }else{
            for (i in 0..6) {
                listDate.add(""+year+"年"+(month-1)+"月")
                month--
            }
        }
        listDate.forEach (::println)
    }




}
