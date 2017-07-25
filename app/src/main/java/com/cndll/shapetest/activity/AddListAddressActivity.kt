package com.cndll.shapetest.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bigkoo.pickerview.OptionsPickerView
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.AddressDetailsResponse
import com.cndll.shapetest.api.bean.response.AddressListResponse
import com.cndll.shapetest.api.bean.response.AddressResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityAddListAddressBinding
import com.google.gson.Gson
import org.json.JSONArray
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * 添加地址
 * */
class AddListAddressActivity : BaseActivity<ActivityAddListAddressBinding>() {
    lateinit var context: Context
    private var province = java.util.ArrayList<String>()
    private var city = java.util.ArrayList<List<String>>()
    private var region = java.util.ArrayList<List<List<String>>>()
    private var ll = ArrayList<AddressResponse.DatasBean>()
    lateinit var provinceId: String
    lateinit var cityId: String
    lateinit var regionId: String
    lateinit var address: String
    var choseType: String = "0"
    lateinit var addressId: String
    lateinit var type:String
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener {
            finish()
        }
        binding.titlebar.title.text = "增加新地址"
        binding.titlebar.titleRight.text = "保存"
        binding.titlebar.titleRight.visibility = View.VISIBLE
        binding.titlebar.titleRight.setTextColor(Color.RED)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_add_list_address)
        context = this
        initView()
    }


    private fun initView() {
        var bundle = this.intent.extras
        type = bundle.getString("type")
        if (type.equals("edit")) {
            binding.addLinDelete.visibility = View.VISIBLE
            addressId = bundle.getString("address_id")
            httpAddressDetalis()
        }

        //选中
        binding.addChose.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                choseType = "1"
            } else {
                choseType = "0"
            }
        }
        //保存
        binding.titlebar.titleRight.setOnClickListener {
            isNull()
        }
        /** 选择地址 */
        binding.linAddChose.setOnClickListener {
            httpAddressCity()
        }
        //删除地址
        binding.deleteAddress.setOnClickListener {

        }
    }

    /**
     * 非空判断
     * */
    private fun isNull() {
        var isNull = true
        var msg = ""
        if (binding.addName.text.trim().equals("")) {
            isNull = false
            msg = "请输入姓名"
        }
        if (binding.addPhone.text.trim().equals("")) {
            isNull = false
            msg = "请输入联系电话"
        }
        if (binding.addCity.text.trim().equals("")) {
            isNull = false
            msg = "请输入所在区"
        }
        if (binding.addDetails.text.trim().equals("")) {
            isNull = false
            msg = "请输入详细地址"
        }
        if (isNull) {
            if(type.equals("edit")){ //编辑地址
                httpUpdateAddress()
            }else{
                httpAddAddress()
            }
            setResult(1)
            finish()
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }
    }

    /**
     * 选择框
     * */
    private fun initOptionPicker() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        var pvOptionsAddress = OptionsPickerView.Builder(this, OptionsPickerView.OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            address = ""
            provinceId = ll[options1].area_id
            cityId = ll[options1].city[options2].area_id
            regionId = ll[options1].city[options2].area[options3].area_id
//            address = province[options1] + " " + city[options1][options2] + " " + region[options1][options2][options3]
            address = ll[options1].area_name + " " + ll[options1].city[options2].area_name + " " + ll[options1].city[options2].area[options3].area_name
            binding.addCity.text = address
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                // .setTitleText("城市选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setSubmitColor(resources.getColor(R.color.colorPrimaryDark))//确定按钮文字颜色
                .setCancelColor(resources.getColor(R.color.contents_text))//取消按钮文字颜色
                .setTitleBgColor(resources.getColor(R.color.white))//标题背景颜色 Night mode
                .setBgColor(resources.getColor(R.color.bg))//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setLinkage(true)//设置是否联动，默认true
                // .setLabels("省", "市", "区")//设置选择的三级单位
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build()
        pvOptionsAddress.setPicker(province, city, region)
        pvOptionsAddress.show()

    }

    /**
     * 地址提交
     * */
    private fun httpAddAddress(){
        AppRequest.getAPI().addAddress("member_address", "address_add", "8a07c70fb9369651f508ba4c9b55c886",choseType,binding.addName.text.toString(),regionId,cityId,binding.addPhone.text.toString(), binding.addDetails.text.toString(),address).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                (t as HttpCodeResponse)
                if (t.code == 200) {
                    Toast.makeText(context,"提交成功！",Toast.LENGTH_LONG).show()
                }
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
            }
        })
    }

    /**
     * 编辑地址提交
     * */
    private fun httpUpdateAddress(){
        AppRequest.getAPI().updateAddress("member_address", "address_edit", "8a07c70fb9369651f508ba4c9b55c886", addressId,choseType,binding.addName.text.toString(),regionId,cityId,binding.addPhone.text.toString(), binding.addDetails.text.toString(),address).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                (t as HttpCodeResponse)
                if (t.code == 200) {
                    Toast.makeText(context,"编辑成功！",Toast.LENGTH_LONG).show()
                }
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
            }
        })
    }


    /**
     * 编辑----地址详情
     * */
    private fun httpAddressDetalis() {
        AppRequest.getAPI().addressDetails("member_address", "address_info", "8a07c70fb9369651f508ba4c9b55c886", addressId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                (t as AddressDetailsResponse)
                if (t.code == 200) {
                    binding.addName.setText(t.datas.address_info.true_name)
                    binding.addPhone.setText(t.datas.address_info.mob_phone)
                    binding.addCity.text = t.datas.address_info.area_info
                    binding.addDetails.setText(t.datas.address_info.address)
                    if (t.datas.address_info.is_default.equals("0")) {
                        binding.addChose.isChecked = false
                    } else if (t.datas.address_info.is_default.equals("1")) {
                        binding.addChose.isChecked = true
                    }
                }
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
            }
        })
    }


    /**
     * 请求地区详情
     * */
    private fun httpAddressCity() {
        AppRequest.getAPI().addressList("area", "areaList").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {

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
                if ((t as AddressResponse).code == 200) {
                    Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
                    jsonTxt(t.datas)
                } else {
                    Toast.makeText(context, "no", Toast.LENGTH_LONG).show()
                }

            }
        })
    }


    /**
     * 转换地址
     * */
    private fun jsonTxt(list: List<AddressResponse.DatasBean>) {
        ll.addAll(list)
        var g = Gson()
        var s = g.toJson(list)
        val jsonArray = JSONArray(s)
        for (i in 0..jsonArray.length() - 1) {
            val jsonObject = jsonArray.getJSONObject(i)
            province.add(jsonObject.getString("area_name"))
            val jsonArray1 = JSONArray(jsonObject.getString("city"))
            val ss = ArrayList<String>()
            val gg = ArrayList<List<String>>()
            for (j in 0..jsonArray1.length() - 1) {
                val jsonObjectj = jsonArray1.getJSONObject(j)
                ss.add(jsonObjectj.getString("area_name"))
                val vv = ArrayList<String>()
                val jsonArray2 = JSONArray(jsonObjectj.getString("area"))
                for (f in 0..jsonArray2.length() - 1) {
                    val jsonObjectf = jsonArray2.getJSONObject(f)
                    vv.add(jsonObjectf.getString("area_name"))
                }
                gg.add(j, vv)
            }
            city.add(i, ss)
            region.add(i, gg)
        }
        initOptionPicker()
    }
}



