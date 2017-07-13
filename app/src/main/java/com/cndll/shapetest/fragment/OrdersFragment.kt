package com.cndll.shapetest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.FragmentOrdersBinding

/**
 * Created by Administrator on 2017/7/7 0007.
 */
class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    override fun initBindingVar() {
    }

    var mNumber:Int?=null
    fun newInstance(number: Int):Fragment {
        var bundle=Bundle()
        bundle.putInt("type",number)
        val fragment = OrdersFragment()
        fragment.arguments=bundle
        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_orders,container)
        mView = binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initView(){

        if(mNumber==1){
            binding.textT.text="1"
        }else if (mNumber==2){
            binding.textT.text="2"
        }else if (mNumber==3){
            binding.textT.text="3"
        }else if (mNumber==4){
            binding.textT.text="4"
        }else if(mNumber==5){
            binding.textT.text="5"
        }else if (mNumber==6){
            binding.textT.text="6"
        }


    }


}