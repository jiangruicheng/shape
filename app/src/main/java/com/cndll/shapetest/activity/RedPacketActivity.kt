package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.PacketAdapter
import com.cndll.shapetest.databinding.ActivityRedPacketBinding

/**
 * 我的红包记录
 * */
class RedPacketActivity : BaseActivity<ActivityRedPacketBinding>() {
    lateinit var context:Context
    var moreList=ArrayList<ContentValues>()
    var adapter:PacketAdapter?=null

    override fun initBindingVar() {
    }

    override fun initTitle() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_red_packet)
        context=this
        initView()
    }

    /** 加载控件*/
    private fun initView(){
        binding.packBack.setOnClickListener { finish() }
        //收到红包记录
        binding.packIn.setOnClickListener {
            binding.packOut.setTextColor(resources.getColor(R.color.possible_result_points))
            binding.packIn.setTextColor(resources.getColor(R.color.sit_title_text))
            binding.packType.text="共收到"
        }
        //发出红包记录
        binding.packOut.setOnClickListener {
            binding.packOut.setTextColor(resources.getColor(R.color.possible_result_points))
            binding.packIn.setTextColor(resources.getColor(R.color.sit_title_text))
            binding.packType.text="共发出"
        }
        initData()

    }


    private fun initData(){
        if(adapter==null){
            adapter= PacketAdapter(moreList,context)
            binding.packetList.adapter=adapter

        }
        var cv=ContentValues()
        cv.put("packetType","个人红包")//姓名 收到的红包
        cv.put("packetTime","5月7号")
        cv.put("packetAccount","22积分")
        var cv1=ContentValues()
        cv1.put("packetType","群红包")
        cv1.put("packetTime","5月7号")
        cv1.put("packetAccount","22积分")
        moreList.add(cv)
        moreList.add(cv1)
        adapter!!.notifyDataSetChanged()
    }


}
