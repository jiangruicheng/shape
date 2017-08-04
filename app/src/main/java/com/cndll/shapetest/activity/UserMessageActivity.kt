package com.cndll.shapetest.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.AboutUsResponse
import com.cndll.shapetest.databinding.ActivityUserMessageBinding
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 关于我们，版权信息等等
 * */
class UserMessageActivity : BaseActivity<ActivityUserMessageBinding>() {
    lateinit var context:Context
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_user_message)
        context=this
        initView()
    }

    /**
     * 绑定信息
     * */
    private fun initView(){
        var bundle=this.intent.extras
        var type=bundle.getString("type")
        if (type.equals("copy")){
            binding.titlebar.title.text="版权信息"
            httpAboutUs(0)
        }else if(type.equals("deal")){
            binding.titlebar.title.text="使用协议"
            httpAboutUs(1)
        }else if (type.equals("help")){
            binding.titlebar.title.text="帮助中心"
            httpAboutUs(2)
        }else if (type.equals("sign")){
            binding.titlebar.title.text="用户协议"
            binding.messageContent.text="用户协议"
        }else if(type.equals("apply")){
            binding.titlebar.title.text="商家协议"
            binding.messageContent.text="商家协议--管理合伙人协议"
        }else if (type.equals("applyDeal")){
            binding.titlebar.title.text="基金捐款协议"
            binding.messageContent.text="基金捐款协议"
        }else if(type.equals("bank")){
            binding.titlebar.title.text="服务协议"
            binding.messageContent.text="服务协议"
        }
    }

    /**
     * 加载关于我们
     * */
    private fun httpAboutUs(num:Int){
        AppRequest.getAPI().aboutUs("article","articleList").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as AboutUsResponse
                if(t.code==200){
                    binding.messageContent.text=t.datas[num].article_content
                }else{
                    Toast.makeText(context,"请求失败",Toast.LENGTH_LONG).show()
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
}
