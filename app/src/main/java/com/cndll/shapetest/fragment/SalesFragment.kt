package com.cndll.shapetest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.FragmentSalesBinding

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class SalesFragment : BaseFragment<FragmentSalesBinding>(){
    var mNumber:Int?=null
    fun newInstance(number: Int): Fragment {
        var bundle=Bundle()
        bundle.putInt("type",number)
        val fragment = SalesFragment()
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
        initBinding(R.layout.fragment_sales,container)
        mView=binding.root
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}