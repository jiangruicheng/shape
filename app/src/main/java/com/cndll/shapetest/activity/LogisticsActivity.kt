package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.LogisticsAdapter
import com.cndll.shapetest.databinding.ActivityLogisticsBinding

/**
 * 物流
 * */
class LogisticsActivity : BaseActivity<ActivityLogisticsBinding>() {
    lateinit var context:Context
    var adapter:LogisticsAdapter?=null
    var moreList=ArrayList<ContentValues>()

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text="物流详情"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_logistics)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        binding.logisSim.setImageURI("http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")

        if(adapter==null){
            adapter= LogisticsAdapter(moreList,context)
            binding.logisDetailsList.adapter=adapter
        }

        var cv=ContentValues()
        cv.put("company","[深圳市]广东省深圳市宝安区流塘公司 已签收签收人： 已签收，感谢使用圆通速递，期待再次为你服务")
        cv.put("time","2017-5-8 22:11:00")
        var cv1=ContentValues()
        cv1.put("company","[深圳市]广东省深圳市宝安区流塘公司 已签收签收人： 已签收，感谢使用圆通速递，期待再次为你服务")
        cv1.put("time","2017-5-8 22:11:00")
        var cv2=ContentValues()
        cv2.put("company","[深圳市]广东省深圳市宝安区流塘公司 已签收签收人： 已签收，感谢使用圆通速递，期待再次为你服务")
        cv2.put("time","2017-5-8 22:11:00")
        moreList.add(cv)
        moreList.add(cv1)
        moreList.add(cv2)
        adapter!!.notifyDataSetChanged()
    }

}
