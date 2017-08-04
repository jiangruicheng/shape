package com.cndll.shapetest.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.LogisticsAdapter
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.LogisticsResponse
import com.cndll.shapetest.databinding.ActivityLogisticsBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 物流
 * */
class LogisticsActivity : BaseActivity<ActivityLogisticsBinding>() {
    lateinit var context:Context
    var adapter:LogisticsAdapter?=null
    var moreList=ArrayList<LogisticsResponse.DatasBean.DeliverInfoBean>()

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.title.text="物流详情"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_logistics)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        var bundle=this.intent.extras
        if(adapter==null){
            adapter= LogisticsAdapter(moreList,context)
            binding.logisDetailsList.adapter=adapter
        }
        httpLogistics(bundle.getString("order_id"))
    }


    /**
     * 物流
     * */
    private fun httpLogistics(order_id:String){
        AppRequest.getAPI().logistics("member_order","search_deliver",SharedPreferenceUtil.read("key",""),order_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable(){
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as LogisticsResponse
                if(t.code==200){
                    binding.logisSim.setImageURI(t.datas.img_url)
                    binding.logisCompany.text="承运公司："+t.datas.express_name
                    binding.logisNum.text="运单编号："+t.datas.shipping_code
                    moreList.addAll(t.datas.deliver_info)
                    adapter!!.notifyDataSetChanged()
                }else{
                    Toast.makeText(context,t.datas.error,Toast.LENGTH_SHORT).show()
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
