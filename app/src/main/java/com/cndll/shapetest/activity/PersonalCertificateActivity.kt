package com.cndll.shapetest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityPersonalCertificateBinding
import com.cndll.shapetest.tools.GetPathVideo
import com.cndll.shapetest.tools.PhotoTools
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import java.io.File

/**
 * 个人认证
 * */
class PersonalCertificateActivity : BaseActivity<ActivityPersonalCertificateBinding>() {
    var photo = PhotoTools()
    lateinit var context: Context
    var type = 1
    var simUserCard: File? = null
    var simCardZ: File? = null
    var simCardF: File? = null

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "个人认证"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_personal_certificate)
        context = this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        binding.simUserCard.setOnClickListener {
            //手持
            type = 1
            photo.getImageFromAlbum(this@PersonalCertificateActivity)
        }
        binding.simCardZ.setOnClickListener {
            //正
            type = 2
            photo.getImageFromAlbum(this@PersonalCertificateActivity)
        }
        binding.simCardF.setOnClickListener {
            //反
            type = 3
            photo.getImageFromAlbum(this@PersonalCertificateActivity)
        }

        binding.authSubmitUser.setOnClickListener {
            isNull()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                if (type == 1) {
                    binding.addCardText.visibility = View.GONE
                    binding.simUserCard.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simUserCard = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 2) {
                    binding.addCardZText.visibility = View.GONE
                    binding.simCardZ.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simCardZ = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 3) {
                    binding.addCardFText.visibility = View.GONE
                    binding.simCardF.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simCardF = File(GetPathVideo.getPath(context, uri))
                }
            }
        }
    }

    //判断非空
    private fun isNull() {
        var msg = ""
        var isNull = true
        if (binding.cerUsernameEdit.text.toString().trim().equals("")) {
            isNull = false
            msg = "请填写姓名"
        }
        if (binding.cerCardEdit.text.toString().trim().equals("")) {
            isNull = false
            msg = "请填写身份证号"
        }
        if (simUserCard == null) {
            isNull = false
            msg = "请选择手持身份证照片"
        }
        if (simCardZ == null) {
            isNull = false
            msg = "请选择身份证正面"
        }
        if (simCardF == null) {
            isNull = false
            msg = "请选择身份证反面"
        }
        if (isNull) {
            Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }
}
