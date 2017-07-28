package com.cndll.shapetest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.api.bean.response.UserInfoResponse
import com.cndll.shapetest.databinding.ActivityUserInfoBinding
import com.cndll.shapetest.tools.*
import com.tencent.connect.UserInfo
import okhttp3.MediaType
import okhttp3.RequestBody
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import kotlin.concurrent.thread

/**
 * 个人信息
 * */
class UserInfoActivity : BaseActivity<ActivityUserInfoBinding>() {
    lateinit var context: Context
     var userIcon: File?=null
    var photo = PhotoTools()
    var userInfo=UserInfoResponse.DatasBean()
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text = "会员信息"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_user_info)
        Constants.verifyStoragePermissions(this@UserInfoActivity)
        context = this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        var bundle=this.intent.extras
        userInfo=bundle.getSerializable("userInfo") as UserInfoResponse.DatasBean
        binding.userIcon.setImageURI(userInfo.member_avatar)
        binding.userNick.setText(userInfo.member_username)
        binding.userId.text=userInfo.member_num
        binding.userClick.setOnClickListener { finish() }
        binding.userSubmit.setOnClickListener { isNull() }
        binding.userIcon.setOnClickListener {
            photo.getImageFromAlbum(this@UserInfoActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                binding.userIcon.setImageURI("file://"+ GetPathVideo.getPath(context,uri))
                var bm=ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                userIcon= ImageFactory.saveFile(bm,"shape.jpg")
                println("userIcon:"+userIcon!!.length())
            }
        }
    }

    private fun isNull() {
        var isNull = true
        var msg = ""
        if (userIcon == null) {
            isNull = false
            msg = "请选择头像"
        }
        if (binding.userNick.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入昵称"
        }

        if (isNull) {
            httpUpdateUserInfo()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }

    /**
     * 修改会员信息
     * */
    private fun httpUpdateUserInfo(){
        var parmes=HashMap<String,RequestBody>()
          parmes.put("act",toreRequestBody("member_info"))
          parmes.put("op",toreRequestBody("memberUpdate"))
          parmes.put("key",toreRequestBody(SharedPreferenceUtil.read("key","")))
          parmes.put("pic\";filename=\""+userIcon!!.name,RequestBody.create(MediaType.parse("image/jpg"), userIcon))
          parmes.put("member_nick",toreRequestBody(binding.userNick.text.toString().trim()))
          AppRequest.getAPI().updateUserInfo(parmes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
              override fun onError(e: Throwable?) {
                  super.onError(e)
                  e!!.printStackTrace()
              }

              override fun onCompleted() {
                  super.onCompleted()
              }

              override fun onNext(t: BaseResponse?) {
                  super.onNext(t)
                  t as HttpCodeResponse
                  if (t.code == 200) {
                      Toast.makeText(context, "修改成功", Toast.LENGTH_LONG).show()
                      finish()
                  } else {
                      Toast.makeText(context, "修改失败", Toast.LENGTH_LONG).show()
                  }
              }
          })
    }

    private fun toreRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }
}
