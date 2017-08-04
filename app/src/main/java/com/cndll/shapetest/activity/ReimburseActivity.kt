package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityReimburseBinding

/**
 * 退款详情--
 * */
class ReimburseActivity : BaseActivity<ActivityReimburseBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text="退款详情"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_reimburse)
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        var bundle=this.intent.extras
        var type=bundle.getInt("type")
        if (type==1){
            binding.reimburseType.setImageDrawable(resources.getDrawable(R.mipmap.audit))
        }else if (type==2){
            binding.reimburseType.setImageDrawable(resources.getDrawable(R.mipmap.no_pass))
        }else if (type==3){
            binding.reimburseType.setImageDrawable(resources.getDrawable(R.mipmap.pass))
        }


    }
}

