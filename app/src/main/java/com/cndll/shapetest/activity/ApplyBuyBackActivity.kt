package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityApplyBuyBackBinding

/**
 * 激励-申请回购
 * */
class ApplyBuyBackActivity : BaseActivity<ActivityApplyBuyBackBinding>() {
    lateinit var context:Context
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="申请提现"
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.titleRight.visibility= View.VISIBLE
        binding.titlebar.titleRight.text="记录"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_apply_buy_back)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        binding.titlebar.titleRight.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("type","buyBack")
            context.startActivity(Intent(context,IntegralActivity::class.java).putExtras(bundle))
        }

        //查看协议
        binding.applyDeal.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("type","applyDeal")
            context.startActivity(Intent(context,UserMessageActivity::class.java).putExtras(bundle))
        }

        binding.applyBuyChose.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.applyBuySubmit.isClickable=true
                binding.applyBuySubmit.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
            }else{
                binding.applyBuySubmit.isClickable=false
                binding.applyBuySubmit.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_gray))
            }
        }

    }

}
