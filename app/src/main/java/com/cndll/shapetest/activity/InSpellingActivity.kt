package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityInSpellingBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.Ini
import com.cndll.shapetest.tools.UtilsUmeng
import com.umeng.socialize.UMAuthListener
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA

/**
 * 拼团---测试分享
 * */
class InSpellingActivity : BaseActivity<ActivityInSpellingBinding>() {
    lateinit var context:Context

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="拼团中"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_in_spelling)
        context=this
        /** 分享 */
        UMShareAPI.get(context).fetchAuthResultWithBundle(this,savedInstanceState, object : UMAuthListener {
            override fun onComplete(p0: SHARE_MEDIA?, p1: Int, p2: MutableMap<String, String>?) {
            }

            override fun onCancel(p0: SHARE_MEDIA?, p1: Int) {
            }

            override fun onError(p0: SHARE_MEDIA?, p1: Int, p2: Throwable?) {
            }

            override fun onStart(p0: SHARE_MEDIA?) {
            }
        })
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        /**
         * 测试分享------------
         * */
        binding.spellingShopImg.setOnClickListener {
            if(Build.VERSION.SDK_INT >= 26){
                Constants.requestCameraPermission(context)
            }
            UtilsUmeng.share(this@InSpellingActivity,Ini.ShareCommunity_Url+43,"22222")
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        UMShareAPI.get(context).onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        UMShareAPI.get(context).release()
    }
}
