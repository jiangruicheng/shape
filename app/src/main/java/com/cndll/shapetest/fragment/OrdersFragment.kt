package com.cndll.shapetest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by Administrator on 2017/7/7 0007.
 */
class OrdersFragment : Fragment() {

    var ss:String?=null
    fun newInstance(dd: String):Fragment {
        val fragment = OrdersFragment()
        fragment.ss = dd
        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd:"+ss)
    }

}