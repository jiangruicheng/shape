package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.AddressAdapter
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.AddressListResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityAddressBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 地址列表
 * */
class AddressActivity : BaseActivity<ActivityAddressBinding>(), AddressAdapter.setOnClickLoction {
    override fun edit(posit: Int) {
        bundle.putString("address_id", moreList[posit].address_id)
        bundle.putString("type", "edit")
        startActivityForResult(Intent(context, AddListAddressActivity::class.java).putExtras(bundle), 1)
    }

    override fun delete(position: Int) {
        AppRequest.getAPI().deleteAddress("member_address","address_del",SharedPreferenceUtil.read("key",""),moreList[position].address_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable(){
            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                (t as HttpCodeResponse)
                if (t.code == 200) {
                    Toast.makeText(context,"删除成功！",Toast.LENGTH_LONG).show()
                    httpAddressList()
                }else{
                    Toast.makeText(context,"删除失败！",Toast.LENGTH_LONG).show()
                }
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
            }
        })
    }

    override fun chose(position: Int) {
        Toast.makeText(context, "选择", Toast.LENGTH_LONG).show()
    }

    lateinit var context: Context
    var adapter: AddressAdapter? = null
    var moreList = ArrayList<AddressListResponse.DatasBean.AddressListBean>()
    var bundle = Bundle()

    override fun initBindingVar() {

    }

    override fun initTitle() {
        binding.titlebar.title.text = "设置"
        binding.titlebar.back.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_address)
        context = this
        initView()
    }

    /** 加载控件 */
    private fun initView() {
        // 跳转地址
        binding.addAddress.setOnClickListener {
            bundle.putString("type", "add")
            startActivityForResult(Intent(context, AddListAddressActivity::class.java).putExtras(bundle), 1)
        }
        if (adapter == null) {
            adapter = AddressAdapter(moreList, context, this)
            binding.addressList.adapter = adapter
        }
        httpAddressList()
    }

    /**
     * 请求地址列表
     * */
    private fun httpAddressList() {
        if(moreList!=null && moreList.size>0){
            moreList.clear()
        }
        AppRequest.getAPI().addressList("member_address", "address_list", SharedPreferenceUtil.read("key","")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {

            override fun onError(e: Throwable?) {
                println("Throwable:" + e.toString())
                super.onError(e)
            }

            override fun onCompleted() {
                println("onCompleted")
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                print("onNext:" + t.toString())
                if ((t as AddressListResponse).code == 200) {
                    moreList.addAll(t.datas.address_list)
                    adapter!!.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "请求失败", Toast.LENGTH_LONG).show()
                }

            }
        })
    }

    /**
     * 新增-编辑地址返回刷新
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == 1) {
            httpAddressList()
        }
    }

}
