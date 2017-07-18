package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityGroupBookingDetailsBinding
/**
 * 拼团订单详情------订单详情
 * */
class GroupBookingDetailsActivity : BaseActivity<ActivityGroupBookingDetailsBinding>() {
    lateinit var context:Context

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="订单详情"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_group_booking_details)
        context=this
        initView()
    }

    /**
     * 判断 中，成功，失败
     * */
    private fun initView(){
        var type=1
        if(type==1){
            //凭团中
            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.group_booking))
        }else if(type==2){
            //成功--等待收货
            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_ok))
        }else if(type==3){
            //失败
            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_delete))
        }else if(type==4){
            //待发货
            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_shipments))
        }else if(type==5){
            //收货
            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_delivery))
        }else if(type==6){
            //待付款
            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.group_payment))
        }

        //1查看团详情
        binding.groupBookingDetails.setOnClickListener {
            context.startActivity(Intent(context,ApplyForRefundActivity::class.java))
        }
        //2查看物流
        binding.groupLogistics.setOnClickListener {  }
        //3确认激励
        binding.groupIncentive.setOnClickListener {  }
        //4
        binding.groupBall.setOnClickListener {  }
    }

}
