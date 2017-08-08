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
import com.cndll.shapetest.tools.*
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

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
    var simCardOther: File? = null
    val c = Calendar.getInstance()
    var dateStart: String = ""
    var dateEnd: String = ""
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "个人认证"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_personal_certificate)
        Constants.verifyStoragePermissions(this@PersonalCertificateActivity)
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
        binding.simCardOther.setOnClickListener {
            //其他
            type = 4
            photo.getImageFromAlbum(this@PersonalCertificateActivity)
        }
        binding.authSubmitUser.setOnClickListener {
            isNull()
        }
        //日期选择
        binding.cerDateStart.setOnClickListener {

            DoubleDatePickerDialog(this@PersonalCertificateActivity, 0, DoubleDatePickerDialog.OnDateSetListener { startDatePicker, startYear, startMonthOfYear, startDayOfMonth ->
                dateStart = String.format("%d-%d-%d\n", startYear,
                        startMonthOfYear + 1, startDayOfMonth)
                binding.cerDateStartText.text = dateStart
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show()
        }
        binding.cerDateEnd.setOnClickListener {
            DoubleDatePickerDialog(this@PersonalCertificateActivity, 0, DoubleDatePickerDialog.OnDateSetListener { startDatePicker, startYear, startMonthOfYear, startDayOfMonth ->
                dateEnd = String.format("%d-%d-%d\n", startYear,
                        startMonthOfYear + 1, startDayOfMonth)
                binding.cerDateEndText.text = dateEnd
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                if (type == 1) {
                    binding.simUserCard.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simUserCard = ImageFactory.saveFile(bm, "shape.jpg")
                }
                if (type == 2) {
                    binding.simCardZ.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simCardZ = ImageFactory.saveFile(bm, "shape.jpg")
                }
                if (type == 3) {
                    binding.simCardF.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simCardF = ImageFactory.saveFile(bm, "shape.jpg")
                }
                if (type == 4) {
                    binding.simCardOther.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simCardOther = ImageFactory.saveFile(bm, "shape.jpg")
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

        if (!Constants.validMobile(binding.cerPhoneEdit.text.toString().trim())) {
            isNull = false
            msg = "请填写正确手机号"
        }

        if (!Constants.validMobile(binding.cerRealPhoneEdit.text.toString().trim())) {
            isNull = false
            msg = "请填写正确手机号"
        }
        if (binding.cerPhoneEdit.text.toString().trim().equals(binding.cerRealPhoneEdit.text.toString().trim())) {
            isNull = false
            msg = "输入手机号需要相同"
        }
        if (binding.cerCardEdit.text.toString().trim().equals("")) {
            isNull = false
            msg = "请填写身份证号"
        }
        if (!Constants.validEmail(binding.cerEmail.text.toString().trim())) {
            isNull = false
            msg = "请填写正确的邮箱"
        }
        if (dateStart.equals("") || dateEnd.equals("")) {
            isNull = false
            msg = "请选择身份证有效期"
        }

        if (Constants.compare_date(binding.cerDateStartText.text.toString().trim(), binding.cerDateEndText.text.toString().trim()) == 1) {
            isNull = false
            msg = "结束日期不能大于，等于开始日期"
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
            httpPerson()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }

    /***
     * 认证
     * */
    private fun httpPerson() {


    }
}
