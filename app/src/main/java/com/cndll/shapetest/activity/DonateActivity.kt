package com.cndll.shapetest.activity

import android.app.Activity
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
    lateinit var context: Context
    var bundles = Bundle()
    override fun initBindingVar() {
    }

    override fun initTitle() {

        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.titleRight.visibility = View.VISIBLE
        binding.titlebar.titleRight.text = "记录"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_donate)
        context = this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {

        var bundle = this.intent.extras
        var type = bundle.getString("type")
        if (type.equals("Donate")) {
            binding.titlebar.title.text = "直捐"
            binding.idUserNum.visibility = View.GONE
            binding.linDonateType.visibility = View.GONE
            binding.titlebar.titleRight.setOnClickListener {
                bundles.putString("type", "donate")
                context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundles))
            }
        } else if (type.equals("Remain")) {
            binding.titlebar.title.text = "积分转增"
            bundles.putString("type", "remain")
            binding.titlebar.titleRight.setOnClickListener {
                //查看记录
                context.startActivity(Intent(context, IntegralActivity::class.java).putExtras(bundles))
            }
            binding.donateChoseText.setOnClickListener {
                //选择选中类型
                startActivityForResult(Intent(context,SetPwdActivity::class.java).putExtras(bundles),101)
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101){
            if(resultCode== 101){
                binding.donateChoseText.text = data!!.extras.getString("chers")
            }

        }
    }


}
