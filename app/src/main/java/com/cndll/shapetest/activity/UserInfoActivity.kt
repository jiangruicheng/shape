package com.cndll.shapetest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityUserInfoBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.GetPathVideo
import com.cndll.shapetest.tools.ImageFactory
import com.cndll.shapetest.tools.PhotoTools
import java.io.File

/**
 * 个人信息
 * */
class UserInfoActivity : BaseActivity<ActivityUserInfoBinding>() {
    lateinit var context: Context
     var userIcon: File?=null
    var photo = PhotoTools()
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

        binding.userClick.setOnClickListener { finish() }
        binding.userSubmit.setOnClickListener { isNull() }
        binding.userIcon.setImageURI("http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        binding.userIcon.setOnClickListener {
            photo.getImageFromAlbum(this@UserInfoActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                binding.userIcon.setImageURI("file://"+ GetPathVideo.getPath(context,uri))
                userIcon=File(GetPathVideo.getPath(context,uri))
                println("d:"+userIcon!!.length())

                var b=ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
//                binding.userIcon.setImageBitmap(b)///测试数据
                var ff= ImageFactory.saveFile(b,"qq")
                println("ff:"+ff!!.length())
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

        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }

    }

}
