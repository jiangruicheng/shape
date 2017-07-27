package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityApplyBinding

/**
 * 申请
 * */
class ApplyActivity : BaseActivity<ActivityApplyBinding>() {
    lateinit var context: Context

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener {
            finish()
        }
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_apply)
        context = this
        initView()
    }

    private fun initView() {
        var bundle = this.intent.extras
        var type: String = bundle.getString("type")
        println("type:" + type)
        if (type.equals("sales")) {
            binding.titlebar.title.text = "申请成为业务员"
        } else if (type.equals("par")) {
            binding.titlebar.title.text = "申请服务合伙人"
        } else if (type.equals("manag")) {
            binding.titlebar.title.text = "申请管理合伙人"
            binding.applyDeal.text = "管理合伙人协议"
            binding.typeName.text="平台"
            binding.userName.visibility=View.GONE
        } else if (type.equals("apply")) {
            binding.titlebar.title.text = "申请商家"
        }

        //查看协议
        binding.applyDeal.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("type","apply")
            context.startActivity(Intent(context,UserMessageActivity::class.java).putExtras(bundle))
        }
        binding.applyChose.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.applySubmit.isClickable=true
                binding.applySubmit.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
            }else{
                binding.applySubmit.isClickable=false
                binding.applySubmit.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_gray))
            }
        }
        binding.applySubmit.setOnClickListener {
            isNull()
        }
    }

    private fun isNull() {
        var isNull = true
        var msg = ""
        if (binding.userName.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入姓名"
        }
        if (binding.appNum.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入推荐码"
        }
        if (isNull) {

        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }


}
