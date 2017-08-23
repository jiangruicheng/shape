package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.ApplyInfoResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityApplyBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil

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
        if (type.equals("sales")) {
            binding.titlebar.title.text = "申请成为业务员"
        } else if (type.equals("par")) {
            binding.titlebar.title.text = "申请服务合伙人"
        } else if (type.equals("apply")) {
            binding.titlebar.title.text = "申请商家"
        } else if (type.equals("manag")) {
            binding.titlebar.title.text = "申请成为合伙人"
            binding.viewLin.visibility = View.GONE
            binding.linAppNum.visibility = View.GONE
            binding.viewTip.visibility = View.GONE
            binding.applyDeal.text = "合伙人协议"
        }

        //查看协议
        binding.applyDeal.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type", "apply")
            context.startActivity(Intent(context, UserMessageActivity::class.java).putExtras(bundle))
        }
        binding.applyChose.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.applySubmit.isClickable = true
                binding.applySubmit.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
            } else {
                binding.applySubmit.isClickable = false
                binding.applySubmit.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_gray))
            }
        }
        binding.applySubmit.setOnClickListener {
            if (type.equals("sales")) {
                isNull()
            } else if (type.equals("manag")) {
                httpApply()
            }
        }
    }

    /**
     * 非空判断
     * */
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
            httpApplyCode()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }

    /**
     * 申请业务员
     * */
    private fun httpApplyCode() {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().applyInviteCode("invite", "invite_info", SharedPreferenceUtil.read("key", ""), binding.userName.text.toString().trim(), "2", binding.appNum.text.toString().trim()), {
            baseResponse ->
            baseResponse as ApplyInfoResponse
            if (baseResponse.code == 200) {
                Toast.makeText(context, baseResponse.datas.error, Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(context, baseResponse.datas.error, Toast.LENGTH_LONG).show()
            }
        })
    }

    /**
     * 申请合伙人
     * */
    private fun httpApply() {
        if (binding.userName.text.toString().trim().equals("")) {
            Toast.makeText(context, "请输入合伙人姓名", Toast.LENGTH_LONG).show()
            return
        }
        ApiUtill.getInstance().getApi(AppRequest.getAPI().applyInvite("invite", "invite_info", SharedPreferenceUtil.read("key", ""), binding.userName.text.toString().trim(), "1"), {
            baseResponse ->
            baseResponse as ApplyInfoResponse
            if (baseResponse.code == 200) {
                Toast.makeText(context, baseResponse.datas.error, Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(context, baseResponse.datas.error, Toast.LENGTH_LONG).show()
            }
        })
    }
}
