package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityDonateBinding

/**
 * 直捐
 * */
class DonateActivity : BaseActivity<ActivityDonateBinding>() {
    lateinit var context:Context
    override fun initBindingVar() {
    }

    override fun initTitle() {

        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.titleRight.visibility=View.VISIBLE
        binding.titlebar.titleRight.text="记录"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_donate)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){

        var bundle=this.intent.extras
        var type=bundle.getString("type")
        if (type.equals("Donate")){
            binding.titlebar.title.text="直捐"
            binding.idUserNum.visibility=View.GONE
            binding.titlebar.titleRight.setOnClickListener {
                var bundles=Bundle()
                bundles.putString("type","donate")
                context.startActivity(Intent(context,IntegralActivity::class.java).putExtras(bundles))
            }
        }else if (type.equals("Remain")){
            binding.titlebar.title.text="积分转增"
            binding.titlebar.titleRight.setOnClickListener {
            var bundles=Bundle()
            bundles.putString("type","remain")
            context.startActivity(Intent(context,IntegralActivity::class.java).putExtras(bundles))
        } }



    }

}
