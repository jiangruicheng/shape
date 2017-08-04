package com.cndll.shapetest.fragment

import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.VouchersAdapter
import com.cndll.shapetest.databinding.FragmentVouchersBinding

/**
 * Created by Administrator on 2017/8/2 0002.
 */

class VouchersFragment : BaseFragment<FragmentVouchersBinding>(){
    var adapter:VouchersAdapter?=null
    var moreList=ArrayList<ContentValues>()
    var mNumber:Int?=null
    fun newInstance(number: Int): Fragment {
        var bundle=Bundle()
        bundle.putInt("type",number)
        val fragment = VouchersFragment()
        fragment.arguments=bundle
        return fragment
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_vouchers,null)
        mView=binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initView(){
        if(mNumber==1){
            if(adapter==null){
                adapter= VouchersAdapter(moreList,context,1)
                binding.vouchersList.adapter=adapter
            }
            httpVouchers()
        }else if (mNumber==2){
            if(adapter==null){
                adapter= VouchersAdapter(moreList,context,2)
                binding.vouchersList.adapter=adapter
            }
            httpVouchers()
        }

    }

    /**
     * 抵用卷
     * */
    private fun httpVouchers(){
        var con: ContentValues = ContentValues()
        con.put("time","1027-5-5")
        con.put("price","22.55")
        moreList.add(con)
        var con1: ContentValues = ContentValues()
        con1.put("time","1027-5-5")
        con1.put("price","22.55")
        moreList.add(con1)
        var con2: ContentValues = ContentValues()
        con2.put("time","1027-5-5")
        con2.put("price","22.55")
        moreList.add(con2)
        adapter!!.notifyDataSetChanged()
    }

}
