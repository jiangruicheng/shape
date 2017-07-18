package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.AddressAdapter
import com.cndll.shapetest.databinding.ActivityAddressBinding

/**
 * 地址列表
 * */
class AddressActivity : BaseActivity<ActivityAddressBinding>(),AddressAdapter.setOnClickLoction{
    override fun edit(posit: Int) {
        bundle.putString("type","edit")
        startActivity(Intent(context,AddListAddressActivity::class.java).putExtras(bundle))
        Toast.makeText(context,"编辑",Toast.LENGTH_LONG).show()
    }

    override fun delete(position: Int) {
        Toast.makeText(context,"删除",Toast.LENGTH_LONG).show()
    }

    override fun chose(position: Int) {
       Toast.makeText(context,"选择",Toast.LENGTH_LONG).show()
    }

    lateinit var context:Context
    var adapter:AddressAdapter?=null
    var moreList=ArrayList<ContentValues>()
    var bundle=Bundle()

    override fun initBindingVar() {

    }

    override fun initTitle() {
        binding.titlebar.title.text = "设置"
        binding.titlebar.back.setOnClickListener{
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_address)
        context=this
        initView()
    }

    /** 加载控件 */
    private fun initView(){
        // 跳转地址
        binding.addAddress.setOnClickListener{
            bundle.putString("type","add")
            startActivity(Intent(context,AddListAddressActivity::class.java).putExtras(bundle))
        }

        if(adapter==null){
            adapter= AddressAdapter(moreList,context,this)
            binding.addressList.adapter=adapter
        }

        var con=ContentValues()
        con.put("arsName","黄大力")
        con.put("arsPhone","13096356365")
        con.put("arsDetails","深圳市宝安区西乡街道办")
        moreList.add(con)
        adapter!!.notifyDataSetChanged()

    }

}
