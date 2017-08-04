package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.widget.ListView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.AdvOrderAdapter
import com.cndll.shapetest.databinding.ActivityAdvanceOrderBinding
import com.cndll.shapetest.tools.Constants

/**
 * 预约订单详情
 * */
class AdvanceOrderActivity : BaseActivity<ActivityAdvanceOrderBinding>() {
    lateinit var context:Context
    var moreList=ArrayList<ContentValues>()
    var adapter:AdvOrderAdapter?=null
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="订单详情"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_advance_order)
        context=this
        initView()
    }
    /**
     * 加载控件
     * */
    private fun initView(){
        var cv=ContentValues()
        cv.put("name","鸡蛋")
        cv.put("num","x1")
        cv.put("account","$22")
        var cv1=ContentValues()
        cv1.put("name","鸡蛋")
        cv1.put("num","x1")
        cv1.put("account","$19")
        var cv2=ContentValues()
        cv2.put("name","鸡蛋")
        cv2.put("num","x1")
        cv2.put("account","$25")
        moreList.add(cv)
        moreList.add(cv1)
        moreList.add(cv2)


        if(adapter==null){
            adapter= AdvOrderAdapter(moreList,context)
            binding.advOrderList.adapter=adapter
            Constants.setListViewHeightBasedOnChildren(binding.advOrderList)
        }
    }

}
