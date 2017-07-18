package com.cndll.shapetest.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bigkoo.pickerview.OptionsPickerView
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityAddListAddressBinding
import org.json.JSONArray
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * 添加地址
 * */
class AddListAddressActivity : BaseActivity<ActivityAddListAddressBinding>() {
    lateinit var context:Context
   private var province = java.util.ArrayList<String>()
   private var city = java.util.ArrayList<List<String>>()
    private var region=java.util.ArrayList<List<List<String>>>()
    lateinit var address:String
    var choseType:Int=0
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener{
            finish()
        }
        binding.titlebar.title.text="增加新地址"
        binding.titlebar.titleRight.text="保存"
        binding.titlebar.titleRight.visibility=View.VISIBLE
        binding.titlebar.titleRight.setTextColor(Color.RED)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_add_list_address)
        context=this
        initView()
    }


    private fun initView(){
        var bundle=this.intent.extras
        var type=bundle.getString("type")
        if(type.equals("edit")){
            binding.addLinDelete.visibility=View.VISIBLE
        }

        //选中
        binding.addChose.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                choseType=1
            }else{
                choseType=2
            }
        }
        //保存
        binding.titlebar.titleRight.setOnClickListener{
            isNull()
        }
        /** 选择地址 */
        binding.linAddChose.setOnClickListener{
            jsonAddress()
        }
        //删除地址
        binding.deleteAddress.setOnClickListener {

        }
    }

    /**
     * 非空判断
     * */
    private fun isNull(){
        var isNull=true
        var msg=""
        if(binding.addName.text.trim().equals("")){
            isNull=false
            msg="请输入姓名"
        }
        if(binding.addPhone.text.trim().equals("")){
            isNull=false
            msg="请输入联系电话"
        }
        if (binding.addCity.text.trim().equals("")){
            isNull=false
            msg="请输入所在区"
        }
        if(binding.addDetails.text.trim().equals("")){
            isNull=false
            msg="请输入详细地址"
        }
        if (isNull){

        }else{
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
            return
        }
    }



    /** 解析地址 */
    private fun jsonAddress(){
        val manager = assets
        try {
            val data = readStream(manager.open("address.txt"))
            val jsonArray = JSONArray(data)
            for (i in 0..jsonArray.length() - 1) {
                val jsonObject = jsonArray.getJSONObject(i)
                province.add(jsonObject.getString("name"))
                val jsonArray1 = JSONArray(jsonObject.getString("subArea"))
                val ss = java.util.ArrayList<String>()
                val gg = java.util.ArrayList<List<String>>()
                for (j in 0..jsonArray1.length() - 1) {
                    val jsonObjectj = jsonArray1.getJSONObject(j)
                    ss.add(jsonObjectj.getString("name"))
                    val vv = java.util.ArrayList<String>()
                    val jsonArray2 = JSONArray(jsonObjectj.getString("subArea"))
                    for (f in 0..jsonArray2.length() - 1) {
                        val jsonObjectf = jsonArray2.getJSONObject(f)
                        vv.add(jsonObjectf.getString("name"))
                    }
                    gg.add(j, vv)
                }
                city.add(i, ss)
                region.add(i, gg)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (er: Exception) {
            er.printStackTrace()
        }
    initOptionPicker()

    }

    fun readStream(`is`: InputStream): String {
        try {
            val bo = ByteArrayOutputStream()
            var i = `is`.read()
            while (i != -1) {
                bo.write(i)
                i = `is`.read()
            }
            return bo.toString()

        } catch (e: IOException) {
            return ""
        }

    }

    private fun initOptionPicker() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

      var  pvOptionsAddress= OptionsPickerView.Builder(this, OptionsPickerView.OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            address = ""
            address = province[options1] + " " + city[options1][options2] + " " + region[options1][options2][options3]
            binding.addCity.text=address
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

}
