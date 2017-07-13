package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityGroupBookingDetailsBinding
/**
 * 拼团订单详情
 * */
class GroupBookingDetailsActivity : BaseActivity<ActivityGroupBookingDetailsBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="订单详情"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_group_booking_details)
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
            //s收货
            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_delivery))
        }

        //查看团详情
        binding.groupBookingDetails.setOnClickListener {

        }

    }

}
