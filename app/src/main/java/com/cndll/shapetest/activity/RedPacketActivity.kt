package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityRedPacketBinding

/**
 * 我的红包记录
 * */
class RedPacketActivity : BaseActivity<ActivityRedPacketBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_red_packet)
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


    }


}
