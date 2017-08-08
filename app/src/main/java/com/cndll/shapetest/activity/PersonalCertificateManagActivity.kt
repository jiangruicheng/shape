package com.cndll.shapetest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityPersonalCertificateManagBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.GetPathVideo
import com.cndll.shapetest.tools.ImageFactory
import com.cndll.shapetest.tools.PhotoTools
import kotlinx.android.synthetic.main.menubutton.view.*
import java.io.File

class PersonalCertificateManagActivity : BaseActivity<ActivityPersonalCertificateManagBinding>() {
    lateinit var context: Context
    var photo = PhotoTools()
    var type = 1
    var simCard: File? = null
    var simJust: File? = null
    var simVersa: File? = null
    var simLoan: File? = null
    var simBusiness: File? = null
    var simWater: File? = null
    var simAuth: File? = null

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
        initBinding(R.layout.activity_personal_certificate_manag)
        context = this
        Constants.verifyStoragePermissions(this@PersonalCertificateManagActivity)
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        binding.perManagHandPhoto.setOnClickListener {
            //手持身份证
            type = 1
            photo.getImageFromAlbum(this@PersonalCertificateManagActivity)
        }
        binding.perManagJust.setOnClickListener {
            //正
            type = 2
            photo.getImageFromAlbum(this@PersonalCertificateManagActivity)
        }
        binding.perManagTurn.setOnClickListener {
            //反
            type = 3
            photo.getImageFromAlbum(this@PersonalCertificateManagActivity)
        }
        binding.perManagBusiness.setOnClickListener {
            //营业执照照片
            type = 4
            photo.getImageFromAlbum(this@PersonalCertificateManagActivity)
        }
        binding.perManagLicence.setOnClickListener {
            //开户许可证照片
            type = 5
            photo.getImageFromAlbum(this@PersonalCertificateManagActivity)
        }
        binding.perManagWater.setOnClickListener {
            //银行流水凭证照片
            type = 6
            photo.getImageFromAlbum(this@PersonalCertificateManagActivity)
        }
        binding.perManagAuth.setOnClickListener {
            //其他证件照片
            type = 7
            photo.getImageFromAlbum(this@PersonalCertificateManagActivity)
        }
        binding.perManagSubmit.setOnClickListener {
            isNull()
        }
    }

    /**
     * 非空判断
     * */
    private fun isNull() {
        var isNull = true
        var msg = ""
        if (binding.perManagCompany.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入企业名称"
        }
        if (binding.perManagName.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入联系人姓名"
        }
        if (!Constants.validMobile(binding.perManagPhone.text.toString().trim())) {
            isNull = false
            msg = "请输入正确手机号"
        }
        if (!Constants.validEmail(binding.perManagMail.text.toString().trim())) {
            isNull = false
            msg = "请输入正确邮箱"
        }
        if (binding.perManagCode.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入公司统一信用代码"
        }
        if (binding.perManagCradNum.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入收款账号"
        }
        if (binding.perManagOpenBank.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入开户行"
        }
        if (binding.perManagCodeRecommend.text.toString().equals("")) {
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
            msg = "请选择开户许可证照片"
        }
        if (simBusiness == null) {
            isNull = false
            msg = "请选择营业执照照片"
        }

        if (simWater == null) {
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
                    binding.perManagHandPhoto.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simCard= ImageFactory.saveFile(bm,"shape.jpg")
                }

                if (type == 2) {
                    binding.perManagJust.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simJust= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 3) {
                    binding.perManagTurn.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simVersa= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 4) {
                    binding.perManagBusiness.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simLoan= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 5) {
                    binding.perManagLicence.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simBusiness= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 6) {
                    binding.perManagWater.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simWater= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 7) {
                    binding.perManagAuth.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simAuth= ImageFactory.saveFile(bm,"shape.jpg")
                }
            }
        }
    }
}
