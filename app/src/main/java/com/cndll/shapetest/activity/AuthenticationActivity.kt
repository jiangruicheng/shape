package com.cndll.shapetest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.AuthenticationResponse
import com.cndll.shapetest.api.bean.response.FileResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityAuthenticationBinding
import com.cndll.shapetest.tools.*
import okhttp3.MediaType
import okhttp3.RequestBody
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File

/***
 * 认证企业
 * */
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {

    var photo = PhotoTools()
    lateinit var context: Context
    var type = 1
    var id = ""

    var simCard: File? = null
    var simJust: File? = null
    var simVersa: File? = null
    var simLoan: File? = null
    var simBusiness: File? = null
    var simOpenAccount: File? = null

    var hand_card = ""
    var just_card = ""
    var back_card = ""
    var business_license = ""
    var opening_permit = ""
    var other = ""

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "企业认证"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_authentication)
        Constants.verifyStoragePermissions(this@AuthenticationActivity)
        context = this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        var bundle = this.intent.extras
        if (bundle == null || !bundle.containsKey("ID") || bundle.getString("ID") == null || bundle.getString("ID").equals("null")) {
            id = ""
        } else {
            id = bundle.getString("ID")
            initData()
        }

        binding.simCard.setOnClickListener {
            //手持
            type = 1
            photo.getImageFromAlbum(this@AuthenticationActivity)
        }
        binding.simJust.setOnClickListener {
            //法人-正
            type = 2
            photo.getImageFromAlbum(this@AuthenticationActivity)
        }
        binding.simVersa.setOnClickListener {
            //法人-反
            type = 3
            photo.getImageFromAlbum(this@AuthenticationActivity)
        }
        binding.simBusiness.setOnClickListener {
            //营业
            type = 4
            photo.getImageFromAlbum(this@AuthenticationActivity)
        }
        binding.simOpenAccount.setOnClickListener {
            //卡U户
            type = 5
            photo.getImageFromAlbum(this@AuthenticationActivity)
        }
        binding.simLoan.setOnClickListener {
            //其他证件照片
            type = 6
            photo.getImageFromAlbum(this@AuthenticationActivity)
        }
        binding.submitAuth.setOnClickListener {
            isNull()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                if (type == 1) {
                    binding.simCard.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simCard = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simCard)
                }

                if (type == 2) {
                    binding.simJust.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simJust = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simJust)
                }
                if (type == 3) {
                    binding.simVersa.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simVersa = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simVersa)
                }
                if (type == 4) {
                    binding.simBusiness.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simBusiness = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simBusiness)
                }
                if (type == 5) {
                    binding.simOpenAccount.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simOpenAccount = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simOpenAccount)
                }
                if (type == 6) {
                    binding.simLoan.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simLoan = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simLoan)
                }
            }
        }
    }

    /**
     * 非空判断
     * */
    private fun isNull() {
        var isNull = true
        var msg = ""
        if (binding.authCompanyEdit.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入企业名称"
        }
        if (binding.legalPersonEdit.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入联系人姓名"
        }
        if (binding.legalCardEdit.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入联系人身份证号"
        }
        if (!Constants.validMobile(binding.authPhoneEdit.text.toString().trim())) {
            isNull = false
            msg = "请输入正确的手机号"
        }
        if (!Constants.validEmail(binding.authEmailEdit.text.toString().trim())) {
            isNull = false
            msg = "请输入正确邮箱"
        }
        if (binding.authCode.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入公司统一信用代码"
        }
        if (binding.authCardNum.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入收款账号"
        }
        if (binding.authCardAddress.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入开户行"
        }

        if (hand_card.equals("")) {
            isNull = false
            msg = "请选择手持照片"
        }
        if (just_card.equals("")) {
            isNull = false
            msg = "请选择手持照片正面"
        }
        if (back_card.equals("")) {
            isNull = false
            msg = "请选择手持照片反面"
        }
        if (business_license.equals("")) {
            isNull = false
            msg = "请选择营业执照"
        }
        if (opening_permit.equals("")) {
            isNull = false
            msg = "请选择开户许可证"
        }
        if (isNull) {
            httpAuth()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            return
        }


    }


    /**
     * 提交数据
     * */
    private fun httpAuth() {
        var parmes = HashMap<String, RequestBody>()
        parmes.put("act", toreRequestBody("certificate"))
        parmes.put("op", toreRequestBody("companySub"))
        parmes.put("key", toreRequestBody(SharedPreferenceUtil.read("key", "")))
        parmes.put("tel_name", toreRequestBody(binding.legalPersonEdit.text.toString().trim()))
        parmes.put("card_num", toreRequestBody(binding.legalCardEdit.text.toString().trim()))
        if (id != null || !id.equals("") || !id.equals("null")) {
            parmes.put("id", toreRequestBody(id))
        }
        parmes.put("tel", toreRequestBody(binding.authPhoneEdit.text.toString().trim()))
        parmes.put("email", toreRequestBody(binding.authEmailEdit.text.toString().trim()))
        parmes.put("com_name", toreRequestBody(binding.authCompanyEdit.text.toString().trim()))
        parmes.put("credit_code", toreRequestBody(binding.authCode.text.toString().trim()))
        parmes.put("opening_bank", toreRequestBody(binding.authCardAddress.text.toString().trim()))
        parmes.put("company_bank", toreRequestBody(binding.authCardNum.text.toString().trim()))
        parmes.put("hand_card", toreRequestBody(hand_card))
        parmes.put("just_card", toreRequestBody(just_card))
        parmes.put("back_card", toreRequestBody(back_card))
        parmes.put("business_license", toreRequestBody(business_license))
        parmes.put("opening_permit", toreRequestBody(opening_permit))
        parmes.put("other", toreRequestBody(other))

        ApiUtill.getInstance().getApi(AppRequest.getAPI().authentica(parmes), {
            baseResponse ->
            baseResponse as HttpCodeResponse
            if (baseResponse.code == 200) {
                Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(context, baseResponse.error_message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    /**
     * 放置数据
     * */
    private fun initData() {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().acthenInfo("certificate", "company", SharedPreferenceUtil.read("key", "key"), id), {
            baseResponse ->
            baseResponse as AuthenticationResponse
            if (baseResponse.code == 200) {
                Toast.makeText(context, baseResponse.datas.describe_info, Toast.LENGTH_SHORT).show()
                binding.simCard.setImageURI(baseResponse.datas.hand_card_img)
                binding.simJust.setImageURI(baseResponse.datas.just_card_img)
                binding.simVersa.setImageURI(baseResponse.datas.back_card_img)
                binding.simBusiness.setImageURI(baseResponse.datas.business_license_img)
                binding.simOpenAccount.setImageURI(baseResponse.datas.opening_permit_img)
                binding.simLoan.setImageURI(baseResponse.datas.other_card_img)
                hand_card = baseResponse.datas.hand_card
                just_card = baseResponse.datas.just_card
                back_card = baseResponse.datas.back_card
                business_license = baseResponse.datas.business_license
                opening_permit = baseResponse.datas.opening_permit
                other = baseResponse.datas.other
                binding.legalPersonEdit.setText(baseResponse.datas.tel_name)
                binding.legalCardEdit.setText(baseResponse.datas.card_num)
                binding.authPhoneEdit.setText(baseResponse.datas.tel)
                binding.authEmailEdit.setText(baseResponse.datas.email)
                binding.authCompanyEdit.setText(baseResponse.datas.com_name)
                binding.authCode.setText(baseResponse.datas.credit_code)
                binding.authCardAddress.setText(baseResponse.datas.opening_bank)
                binding.authCardNum.setText(baseResponse.datas.company_bank)
            } else {
                Toast.makeText(context, baseResponse.error_message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    /**
     * 上查文件
     * */
    private fun uploadFile(file: File?) {
        var parmes = HashMap<String, RequestBody>()
        parmes.put("act", toreRequestBody("sns_album"))
        parmes.put("op", toreRequestBody("file_upload"))
        parmes.put("key", toreRequestBody(SharedPreferenceUtil.read("key", "")))
        parmes.put("file\";filename=\"" + file!!.name, RequestBody.create(MediaType.parse("image/jpg"), file))
        AppRequest.getAPI().uploadFile(parmes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as FileResponse
                if (t.code == 200) {
                    Toast.makeText(context, t.datas.file_name, Toast.LENGTH_SHORT).show()
                    if (type == 1) {
                        hand_card = t.datas.file_name
                    } else if (type == 2) {
                        just_card = t.datas.file_name
                    } else if (type == 3) {
                        back_card = t.datas.file_name
                    } else if (type == 4) {
                        business_license = t.datas.file_name
                    } else if (type == 5) {
                        opening_permit = t.datas.file_name
                    } else if (type == 6) {
                        other = t.datas.file_name
                    }

                } else {
                    Toast.makeText(context, t.datas.error, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }
        })
    }

    private fun toreRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }

}
