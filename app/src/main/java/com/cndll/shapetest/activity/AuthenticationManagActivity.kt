package com.cndll.shapetest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityAuthenticationManagBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.GetPathVideo
import com.cndll.shapetest.tools.PhotoTools
import java.io.File

class AuthenticationManagActivity : BaseActivity<ActivityAuthenticationManagBinding>() {
    lateinit var context: Context
    var photo = PhotoTools()
    var type = 1
    var simCard: File? = null
    var simJust: File? = null
    var simVersa: File? = null
    var simLoan: File? = null
    var simBusiness: File? = null

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "申请成为管理合伙人"
        binding.titlebar.back.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_authentication_manag)
        context = this
        Constants.verifyStoragePermissions(this@AuthenticationManagActivity)
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        binding.authManagHandPhoto.setOnClickListener {
            //手持身份证照片
            type = 1
            photo.getImageFromAlbum(this@AuthenticationManagActivity)
        }
        binding.authManagJust.setOnClickListener {
            //正面
            type = 2
            photo.getImageFromAlbum(this@AuthenticationManagActivity)
        }
        binding.authManagVersa.setOnClickListener {
            //反面
            type = 3
            photo.getImageFromAlbum(this@AuthenticationManagActivity)
        }
        binding.authManagWater.setOnClickListener {
            //银行流水
            type = 4
            photo.getImageFromAlbum(this@AuthenticationManagActivity)
        }
        binding.authManagRest.setOnClickListener {
            //选填
            type = 5
            photo.getImageFromAlbum(this@AuthenticationManagActivity)
        }
        binding.authManagSubmit.setOnClickListener { isNull() }

    }

    /**
     * 非空判断
     * */
    private fun isNull() {
        var isNull = true
        var msg = ""
        if (binding.authManName.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入姓名"
        }
        if (!Constants.validMobile(binding.authManPhone.text.toString().trim())) {
            isNull = false
            msg = "请输入正确的手机号"
        }
        if (!Constants.validMobile(binding.authManagPhone.text.toString().trim())) {
            isNull = false
            msg = "请输入正确的手机号"
        }
        if (binding.authManagCard.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入身份证号"
        }
        if (!Constants.validEmail(binding.authManagMail.text.toString().trim())) {
            isNull = false
            msg = "请输入正确邮箱"
        }
        if (binding.authManagCardNum.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入收款账号"
        }
        if (binding.authManagReferralCode.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入推荐码"
        }
        if (simCard == null) {
            isNull = false
            msg = "请选择手持身份证照片"
        }
        if (simJust == null) {
            isNull = false
            msg = "请选择身份证照片正面"
        }
        if (simVersa == null) {
            isNull = false
            msg = "请选择身份证照片反面"
        }
        if (simLoan == null) {
            isNull = false
            msg = "请选择银行流水凭证照片"
        }
        if (isNull) {

        } else {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            return
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                if (type == 1) {
                    binding.authManagHandPhoto.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simCard = File(GetPathVideo.getPath(context, uri))
                }

                if (type == 2) {
                    binding.authManagJust.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simJust = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 3) {
                    binding.authManagVersa.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simVersa = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 4) {
                    binding.authManagWater.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simLoan = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 5) {
                    binding.authManagRest.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simBusiness = File(GetPathVideo.getPath(context, uri))
                }
            }
        }
    }

}
