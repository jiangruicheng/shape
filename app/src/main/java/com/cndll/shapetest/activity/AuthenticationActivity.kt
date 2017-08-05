package com.cndll.shapetest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityAuthenticationBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.GetPathVideo
import com.cndll.shapetest.tools.ImageFactory
import com.cndll.shapetest.tools.PhotoTools
import java.io.File

/***
 * 认证企业
 * */
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {

    var photo = PhotoTools()
    lateinit var context: Context
    var type = 1
    var simCard: File? = null
    var simJust: File? = null
    var simVersa: File? = null
    var simLoan: File? = null
    var simBusiness: File? = null
    var simOpenAccount: File? = null

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
        binding.simLoan.setOnClickListener {
            //其他证件照片
            type = 4
            photo.getImageFromAlbum(this@AuthenticationActivity)
        }
        binding.simBusiness.setOnClickListener {
            //营业
            type = 5
            photo.getImageFromAlbum(this@AuthenticationActivity)
        }
        binding.simOpenAccount.setOnClickListener {
            //卡U户
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
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simCard= ImageFactory.saveFile(bm,"shape.jpg")
                }

                if (type == 2) {
                    binding.simJust.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simJust= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 3) {
                    binding.simVersa.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simVersa= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 4) {
                    binding.simLoan.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simLoan= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 5) {
                    binding.simBusiness.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simBusiness= ImageFactory.saveFile(bm,"shape.jpg")
                }
                if (type == 6) {
                    binding.simOpenAccount.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                    simOpenAccount= ImageFactory.saveFile(bm,"shape.jpg")
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
        if (!Constants.validMobile(binding.authPhoneEdit.text.toString().trim())){
            isNull=false
            msg="请输入正确的手机号"
        }
        if(!Constants.validEmail(binding.authEmailEdit.text.toString().trim())){
            isNull=false
            msg="请输入正确邮箱"
        }
        if (binding.authCode.text.toString().trim().equals("")){
            isNull=false
            msg="请输入公司统一信用代码"
        }
        if(binding.authCardNum.text.toString().trim().equals("")){
            isNull=false
            msg="请输入收款账号"
        }
        if(binding.authCardAddress.text.toString().trim().equals("")){
            isNull=false
            msg="请输入开户行"
        }
        if (simCard==null){
            isNull=false
            msg="请选择手持照片"
        }
        if(simJust==null){
            isNull=false
            msg="请选择手持照片正面"
        }
        if(simVersa==null){
            isNull=false
            msg="请选择手持照片反面"
        }
        if(simBusiness==null){
            isNull=false
            msg="请选择营业执照"
        }
        if(simOpenAccount==null){
            isNull=false
            msg="请选择开户许可证"
        }
        if(isNull){

        }else{
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
            return
        }


    }


}
