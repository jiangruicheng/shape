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
        context=this
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
            //信用
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
                    binding.cardText.visibility = View.GONE
                    binding.simCard.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simCard = File(GetPathVideo.getPath(context, uri))
                }

                if (type == 2) {
                    binding.justText.visibility = View.GONE
                    binding.simJust.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simJust = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 3) {
                    binding.versaText.visibility = View.GONE
                    binding.simVersa.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simVersa = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 4) {
                    binding.loanText.visibility = View.GONE
                    binding.simLoan.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simLoan = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 5) {
                    binding.businessText.visibility = View.GONE
                    binding.simBusiness.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simBusiness = File(GetPathVideo.getPath(context, uri))
                }
                if (type == 6) {
                    binding.openAccount.visibility = View.GONE
                    binding.simOpenAccount.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    simOpenAccount = File(GetPathVideo.getPath(context, uri))
                }
            }
        }
    }


    private fun isNull() {
    }


}
