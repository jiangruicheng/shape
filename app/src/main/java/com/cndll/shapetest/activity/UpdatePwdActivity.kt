package com.cndll.shapetest.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityUpdatePwdBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 修改密码
 * */
class UpdatePwdActivity : BaseActivity<ActivityUpdatePwdBinding>() {
    private lateinit var context:Context
    override fun initBindingVar() {

    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_update_pwd)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        binding.titlebar.title.text="修改登录密码"
        binding.updatePwd.setOnClickListener {
            isNull()
        }
    }

    private fun isNull(){
        var isNull=true
        var msg=""
        if(binding.pwdUpdate.text.toString().trim().equals("")){
            isNull=false
            msg="请输入原密码"
        }
        if(binding.newPwdUpdate.text.toString().trim().equals("")){
            isNull=false
            msg="请输入新密码"
        }
        if(binding.newPwdUpdate.text.toString().trim().equals(binding.pwdUpdate.text.toString().trim())){
            isNull=false
            msg="输入的密码不能相同"
        }
        if (6>binding.newPwdUpdate.text.toString().trim().length ||binding.newPwdUpdate.text.toString().trim().length>20){
            isNull=false
            msg="请输入6至20位密码"
        }
        if(isNull){
            updatePwd()
        }else{
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
            return
        }

    }

    /**
     * 修改登录密码
     * */
    private fun updatePwd(){
        AppRequest.getAPI().updateLoginPwd("login","passwordUpdate",SharedPreferenceUtil.read("key",""),binding.pwdUpdate.text.toString().trim(),binding.newPwdUpdate.text.toString().trim()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object  : BaseObservable(){
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
                if (t.code==200){
                    Toast.makeText(context,"修改成功",Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(context,t.error_massage,Toast.LENGTH_LONG).show()
                }
            }
        })
    }


}
