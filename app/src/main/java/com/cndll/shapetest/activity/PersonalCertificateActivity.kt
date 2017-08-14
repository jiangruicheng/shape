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
import com.cndll.shapetest.api.bean.response.FileResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.api.bean.response.PersonalCerticateResponse
import com.cndll.shapetest.databinding.ActivityPersonalCertificateBinding
import com.cndll.shapetest.tools.*
import okhttp3.MediaType
import okhttp3.RequestBody
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

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
    var id = ""
    var hand_card: String = ""
    var just_card: String = ""
    var back_card: String = ""
    var other_card: String = ""
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
        var bundle = this.intent.extras
        if (bundle == null || !bundle.containsKey("ID") || bundle.getString("ID") == null || bundle.getString("ID").equals("null")) {
            id = ""
        } else {
            id = bundle.getString("ID")
            httpPersonInfo()
        }
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
                    uploadFile(simUserCard)
                }
                if (type == 2) {
                    binding.simCardZ.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simCardZ = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simCardZ)
                }
                if (type == 3) {
                    binding.simCardF.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simCardF = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simCardF)
                }
                if (type == 4) {
                    binding.simCardOther.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm = ImageFactory.getSmallBitmap(GetPathVideo.getPath(context, uri))
                    simCardOther = ImageFactory.saveFile(bm, "shape.jpg")
                    uploadFile(simCardOther)
                }
            }
        }
    }

    //判断非空
    private fun isNull() {
        var msg = ""
        var isNull = true
        if (binding.cerCardNum.text.toString().trim().equals("")) {
            isNull = false
            msg = "请填写收款账号"
        }

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
        if (!binding.cerPhoneEdit.text.toString().trim().equals(binding.cerRealPhoneEdit.text.toString().trim())) {
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
        if (hand_card.equals("")) {
            isNull = false
            msg = "请选择手持身份证照片"
        }
        if (just_card.equals("")) {
            isNull = false
            msg = "请选择身份证正面"
        }
        if (back_card.equals("")) {
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

    /**
     * 认证信息
     * */
    private fun httpPersonInfo() {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().personalInfo("certificate", "personal", SharedPreferenceUtil.read("key", ""), id), {
            baseResponse ->
            baseResponse as PersonalCerticateResponse
            if (baseResponse.code == 200) {
                binding.cerUsernameEdit.setText(baseResponse.datas.name)
                binding.cerUsernameEdit.isClickable = false
                binding.cerUsernameEdit.isFocusable = false
                binding.cerCardEdit.setText(baseResponse.datas.card_num)
                binding.cerCardEdit.isClickable = false
                binding.cerCardEdit.isFocusable = false
                binding.cerCardNum.setText(baseResponse.datas.bank_card_num)
                binding.cerCardNum.isClickable = false
                binding.cerCardNum.isFocusable = false
                binding.cerPhoneEdit.setText(baseResponse.datas.phone)
                binding.cerPhoneEdit.isClickable = false
                binding.cerPhoneEdit.isFocusable = false
                binding.cerRealPhoneEdit.setText(baseResponse.datas.phone)
                binding.cerRealPhoneEdit.isClickable = false
                binding.cerRealPhoneEdit.isFocusable = false
                binding.cerEmail.setText(baseResponse.datas.email)
                binding.cerDateStartText.text = Constants.strDate(baseResponse.datas.card_start_time)
                binding.cerDateEndText.text = Constants.strDate(baseResponse.datas.card_end_time)
                binding.cerDateStart.isClickable = false
                binding.cerDateStart.isFocusable = false
                binding.cerDateStart.isEnabled = false
                binding.cerDateEnd.isEnabled = false
                binding.cerDateEnd.isClickable = false
                binding.cerDateEnd.isFocusable = false
                hand_card = baseResponse.datas.hand_card
                just_card = baseResponse.datas.just_card
                back_card = baseResponse.datas.back_card
                other_card = baseResponse.datas.other_card
                binding.simUserCard.setImageURI(baseResponse.datas.hand_card_img)
                binding.simCardZ.setImageURI(baseResponse.datas.just_card_img)
                binding.simCardF.setImageURI(baseResponse.datas.back_card_img)
                binding.simCardOther.setImageURI(baseResponse.datas.other_card_img)
                Toast.makeText(context, baseResponse.datas.describe_personal, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, baseResponse.error_message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    /***
     * 认证
     * */
    private fun httpPerson() {
        var parmes = HashMap<String, RequestBody>()
        parmes.put("act", toreRequestBody("certificate"))
        parmes.put("op", toreRequestBody("personalSub"))
        parmes.put("key", toreRequestBody(SharedPreferenceUtil.read("key", "")))
        parmes.put("name", toreRequestBody(binding.cerUsernameEdit.text.toString().trim()))
        parmes.put("card_num", toreRequestBody(binding.cerCardEdit.text.toString().trim()))
        if (id != null || !id.equals("") || !id.equals("null")) {
            parmes.put("id", toreRequestBody(id))
        }
        parmes.put("bank_card_num", toreRequestBody(binding.cerCardNum.text.toString().trim()))
        parmes.put("member_phone", toreRequestBody(binding.cerRealPhoneEdit.text.toString().trim()))
        parmes.put("member_email", toreRequestBody(binding.cerEmail.text.toString().trim()))
        parmes.put("card_start_time", toreRequestBody(Constants.dateToStamp(binding.cerDateStartText.text.toString().trim())))
        parmes.put("card_end_time", toreRequestBody(Constants.dateToStamp(binding.cerDateEndText.text.toString().trim())))
        parmes.put("hand_card", toreRequestBody(hand_card))
        parmes.put("just_card", toreRequestBody(just_card))
        parmes.put("back_card", toreRequestBody(back_card))
        parmes.put("other_card", toreRequestBody(other_card))

        AppRequest.getAPI().personal(parmes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(context, t.error_message, Toast.LENGTH_SHORT).show()
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
                        other_card = t.datas.file_name
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
